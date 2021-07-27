/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.coyote.http2;

import org.apache.coyote.InputBuffer;
import org.apache.coyote.OutputBuffer;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.apache.coyote.http2.HpackDecoder.HeaderEmitter;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.res.StringManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

public class Stream extends AbstractStream implements HeaderEmitter {

    private static final Log log = LogFactory.getLog(Stream.class);
    private static final StringManager sm = StringManager.getManager(Stream.class);
    private final Http2UpgradeHandler handler;
    private final StreamStateMachine state;
    // TODO: Only create these objects if needed and null them when finished
    private final Request coyoteRequest;
    private final Response coyoteResponse = new Response();
    private final StreamInputBuffer inputBuffer;
    private final StreamOutputBuffer outputBuffer = new StreamOutputBuffer();
    private volatile int weight = Constants.DEFAULT_WEIGHT;


    public Stream(Integer identifier, Http2UpgradeHandler handler) {
        this(identifier, handler, null);
    }


    public Stream(Integer identifier, Http2UpgradeHandler handler, Request coyoteRequest) {
        super(identifier);
        this.handler = handler;
        setParentStream(handler);
        setWindowSize(handler.getRemoteSettings().getInitialWindowSize());
        state = new StreamStateMachine(this);
        if (coyoteRequest == null) {
            // HTTP/2 new request
            this.coyoteRequest = new Request();
            this.inputBuffer = new StreamInputBuffer();
            this.coyoteRequest.setInputBuffer(inputBuffer);
        } else {
            // HTTP/1.1 upgrade
            this.coyoteRequest = coyoteRequest;
            this.inputBuffer = null;
            // Headers have been populated by this point
            state.receivedStartOfHeaders();
            // TODO Assuming the body has been read at this point is not valid
            state.recievedEndOfStream();
        }
        this.coyoteResponse.setOutputBuffer(outputBuffer);
        this.coyoteRequest.setResponse(coyoteResponse);
    }


    public void rePrioritise(AbstractStream parent, boolean exclusive, int weight) {
        if (log.isDebugEnabled()) {
            log.debug(sm.getString("stream.reprioritisation.debug",
                    getConnectionId(), getIdentifier(), Boolean.toString(exclusive),
                    parent.getIdentifier(), Integer.toString(weight)));
        }

        // Check if new parent is a descendant of this stream
        if (isDescendant(parent)) {
            parent.detachFromParent();
            getParentStream().addChild(parent);
        }

        if (exclusive) {
            // Need to move children of the new parent to be children of this
            // stream. Slightly convoluted to avoid concurrent modification.
            Iterator<AbstractStream> parentsChildren = parent.getChildStreams().iterator();
            while (parentsChildren.hasNext()) {
                AbstractStream parentsChild = parentsChildren.next();
                parentsChildren.remove();
                this.addChild(parentsChild);
            }
        }
        parent.addChild(this);
        this.weight = weight;
    }


    public void reset(long errorCode) {
        if (log.isDebugEnabled()) {
            log.debug(sm.getString("stream.reset.debug", getConnectionId(), getIdentifier(),
                    Long.toString(errorCode)));
        }
        state.receiveReset();
    }


    void checkState(FrameType frameType) throws Http2Exception {
        state.checkFrameType(frameType);
    }


    @Override
    public synchronized void incrementWindowSize(int windowSizeIncrement) throws Http2Exception {
        // If this is zero then any thread that has been trying to write for
        // this stream will be waiting. Notify that thread it can continue. Use
        // notify all even though only one thread is waiting to be on the safe
        // side.
        boolean notify = getWindowSize() < 1;
        super.incrementWindowSize(windowSizeIncrement);
        if (notify && getWindowSize() > 0) {
            notifyAll();
        }
    }


    private synchronized int reserveWindowSize(int reservation) throws IOException {
        long windowSize = getWindowSize();
        while (windowSize < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Possible shutdown / rst or similar. Use an IOException to
                // signal to the client that further I/O isn't possible for this
                // Stream.
                throw new IOException(e);
            }
            windowSize = getWindowSize();
        }
        int allocation;
        if (windowSize < reservation) {
            allocation = (int) windowSize;
        } else {
            allocation = reservation;
        }
        decrementWindowSize(allocation);
        return allocation;
    }


    @Override
    public void emitHeader(String name, String value, boolean neverIndex) {
        if (log.isDebugEnabled()) {
            log.debug(sm.getString("stream.header.debug", getConnectionId(), getIdentifier(),
                    name, value));
        }

        switch(name) {
        case ":method": {
            coyoteRequest.method().setString(value);
            break;
        }
        case ":path": {
            coyoteRequest.requestURI().setString(value);
            // TODO: This is almost certainly wrong and needs to be decoded
            coyoteRequest.decodedURI().setString(value);
            break;
        }
        case ":authority": {
            int i = value.lastIndexOf(':');
            if (i > -1) {
                coyoteRequest.serverName().setString(value.substring(0, i));
                coyoteRequest.setServerPort(Integer.parseInt(value.substring(i + 1)));
            } else {
                coyoteRequest.serverName().setString(value);
                boolean secure = Boolean.parseBoolean(handler.getProperty("secure"));
                if (secure) {
                    coyoteRequest.setServerPort(443);
                } else {
                    coyoteRequest.setServerPort(80);
                }
            }
            break;
        }
        default: {
            // Assume other HTTP header
            coyoteRequest.getMimeHeaders().addValue(name).setString(value);
        }
        }
    }


    void writeHeaders() {
        try {
            handler.writeHeaders(this, coyoteResponse);
        } catch (IOException e) {
            // TODO Handle this
            e.printStackTrace();
        }
    }


    void flushData() {
        if (log.isDebugEnabled()) {
            log.debug(sm.getString("stream.write", getConnectionId(), getIdentifier()));
        }
        try {
            outputBuffer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    protected final String getConnectionId() {
        return getParentStream().getConnectionId();
    }


    @Override
    protected int getWeight() {
        return weight;
    }


    Request getCoyoteRequest() {
        return coyoteRequest;
    }


    Response getCoyoteResponse() {
        return coyoteResponse;
    }


    ByteBuffer getInputByteBuffer() {
        return inputBuffer.getInBuffer();
    }


    void receivedStartOfHeaders() {
        state.receivedStartOfHeaders();
    }


    void receivedEndOfStream() {
        state.recievedEndOfStream();
    }


    void sentEndOfStream() {
        state.sentEndOfStream();
    }


    StreamOutputBuffer getOutputBuffer() {
        return outputBuffer;
    }


    void sendRst() {
        state.sendReset();
    }


    boolean isActive() {
        return state.isActive();
    }


    void closeIfIdle() {
        state.closeIfIdle();
    }


    class StreamOutputBuffer implements OutputBuffer {

        private final ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
        private volatile long written = 0;
        private volatile boolean closed = false;

        /* The write methods are synchronized to ensure that only one thread at
         * a time is able to access the buffer. Without this protection, a
         * client that performed concurrent writes could corrupt the buffer.
         */

        @Override
        public synchronized int doWrite(ByteChunk chunk) throws IOException {
            if (closed) {
                throw new IllegalStateException(
                        sm.getString("stream.closed", getConnectionId(), getIdentifier()));
            }
            int len = chunk.getLength();
            int offset = 0;
            while (len > 0) {
                int thisTime = Math.min(buffer.remaining(), len);
                buffer.put(chunk.getBytes(), chunk.getOffset() + offset, thisTime);
                offset += thisTime;
                len -= thisTime;
                if (len > 0 && !buffer.hasRemaining()) {
                    // Only flush if we have more data to write and the buffer
                    // is full
                    flush(true);
                }
            }
            written += offset;
            return offset;
        }

        public synchronized void flush() throws IOException {
            flush(false);
        }

        private synchronized void flush(boolean writeInProgress) throws IOException {
            if (!coyoteResponse.isCommitted()) {
                coyoteResponse.sendHeaders();
            }
            if (buffer.position() == 0) {
                // Buffer is empty. Nothing to do.
                return;
            }
            buffer.flip();
            int left = buffer.remaining();
            while (left > 0) {
                int streamReservation  = reserveWindowSize(left);
                while (streamReservation > 0) {
                    int connectionReservation =
                                handler.reserveWindowSize(Stream.this, streamReservation);
                    // Do the write
                    handler.writeBody(Stream.this, buffer, connectionReservation,
                            !writeInProgress && closed && left == connectionReservation);
                    streamReservation -= connectionReservation;
                    left -= connectionReservation;
                    buffer.position(buffer.position() + connectionReservation);

                }
            }
            buffer.clear();
        }

        @Override
        public long getBytesWritten() {
            return written;
        }

        public void close() {
            closed = true;
        }

        public boolean isClosed() {
            return closed;
        }

        /**
         * @return <code>true</code> if it is certain that the associated
         *         response has no body.
         */
        public boolean hasNoBody() {
            return ((written == 0) && closed);
        }
    }


    class StreamInputBuffer implements InputBuffer {

        /* Two buffers are required to avoid various multi-threading issues.
         * These issues arise from the fact that the Stream (or the
         * Request/Response) used by the application is processed in one thread
         * but the connection is processed in another. Therefore it is possible
         * that a request body frame could be received before the application
         * is ready to read it. If it isn't buffered, processing of the
         * connection (and hence all streams) would block until the application
         * read the data. Hence the incoming data has to be buffered.
         * If only one buffer was used then it could become corrupted if the
         * connection thread is trying to add to it at the same time as the
         * application is read it. While it should be possible to avoid this
         * corruption by careful use of the buffer it would still require the
         * same copies as using two buffers and the behaviour would be less
         * clear.
         *
         * The buffers are created lazily because 32K per stream quickly adds
         * up to a lot of memory and most requests do not have bodies.
         */
        // This buffer is used to populate the ByteChunk passed in to the read
        // method
        private byte[] outBuffer;
        // This buffer is the destination for incoming data. It is normally is
        // 'write mode'.
        private volatile ByteBuffer inBuffer;

        @Override
        public int doRead(ByteChunk chunk) throws IOException {

            ensureBuffersExist();

            int written = 0;

            // Ensure that only one thread accesses inBuffer at a time
            synchronized (inBuffer) {
                while (inBuffer.position() == 0 && state.isFrameTypePermitted(FrameType.DATA)) {
                    // Need to block until some data is written
                    try {
                        inBuffer.wait();
                    } catch (InterruptedException e) {
                        // Possible shutdown / rst or similar. Use an
                        // IOException to signal to the client that further I/O
                        // isn't possible for this Stream.
                        throw new IOException(e);
                    }
                }

                if (inBuffer.position() > 0) {
                    // Data remains in the in buffer. Copy it to the out buffer.
                    inBuffer.flip();
                    written = inBuffer.remaining();
                    inBuffer.get(outBuffer, 0, written);
                    inBuffer.clear();
                } else if (!state.isFrameTypePermitted(FrameType.DATA)) {
                    return -1;
                } else {
                    // Should never happen
                    throw new IllegalStateException();
                }
            }

            chunk.setBytes(outBuffer, 0,  written);

            // Increment client-side flow control windows by the number of bytes
            // read
            handler.writeWindowUpdate(Stream.this, written);

            return written;
        }


        public ByteBuffer getInBuffer() {
            ensureBuffersExist();
            return inBuffer;
        }


        private void ensureBuffersExist() {
            if (inBuffer != null) {
                return;
            }
            synchronized (this) {
                if (inBuffer == null) {
                    inBuffer = ByteBuffer.allocate(16 * 1024);
                    outBuffer = new byte[16 * 1024];
                }
            }
        }
    }
}
