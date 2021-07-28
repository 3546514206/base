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
package org.apache.coyote.http11.upgrade;

import org.apache.tomcat.util.net.AbstractEndpoint.Handler.SocketState;
import org.apache.tomcat.util.net.SSLSupport;
import org.apache.tomcat.util.net.SocketStatus;
import org.apache.tomcat.util.net.SocketWrapperBase;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class UpgradeProcessorInternal extends UpgradeProcessorBase {

    private final InternalHttpUpgradeHandler internalHttpUpgradeHandler;

    public UpgradeProcessorInternal(SocketWrapperBase<?> wrapper, ByteBuffer leftOverInput,
            InternalHttpUpgradeHandler internalHttpUpgradeHandler) {
        super(wrapper, leftOverInput, internalHttpUpgradeHandler);
        this.internalHttpUpgradeHandler = internalHttpUpgradeHandler;
        /*
         * Leave timeouts in the hands of the upgraded protocol.
         */
        wrapper.setReadTimeout(INFINITE_TIMEOUT);
        wrapper.setWriteTimeout(INFINITE_TIMEOUT);

        internalHttpUpgradeHandler.setSocketWrapper(wrapper);
    }


    @Override
    public SocketState dispatch(SocketStatus status) {
        return internalHttpUpgradeHandler.upgradeDispatch(status);
    }


    @Override
    public final void setSslSupport(SSLSupport sslSupport) {
        internalHttpUpgradeHandler.setSslSupport(sslSupport);
    }


    @Override
    public void pause() {
        internalHttpUpgradeHandler.pause();
    }


    // --------------------------------------------------- AutoCloseable methods

    @Override
    public void close() throws Exception {
        internalHttpUpgradeHandler.destroy();
    }


    // --------------------------------------------------- WebConnection methods

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }
}
