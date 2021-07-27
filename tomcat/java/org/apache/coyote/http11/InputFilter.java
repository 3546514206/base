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

package org.apache.coyote.http11;

import org.apache.coyote.InputBuffer;
import org.apache.coyote.Request;
import org.apache.tomcat.util.buf.ByteChunk;

import java.io.IOException;

/**
 * Input filter interface.
 *
 * @author Remy Maucherat
 */
public interface InputFilter extends InputBuffer {

    /**
     * Some filters need additional parameters from the request. All the
     * necessary reading can occur in that method, as this method is called
     * after the request header processing is complete.
     */
    void setRequest(Request request);


    /**
     * Make the filter ready to process the next request.
     */
    void recycle();


    /**
     * Get the name of the encoding handled by this filter.
     */
    ByteChunk getEncodingName();


    /**
     * Set the next buffer in the filter pipeline.
     */
    void setBuffer(InputBuffer buffer);


    /**
     * End the current request.
     *
     * @return 0 is the expected return value. A positive value indicates that
     * too many bytes were read. This method is allowed to use buffer.doRead
     * to consume extra bytes. The result of this method can't be negative (if
     * an error happens, an IOException should be thrown instead).
     */
    long end()
        throws IOException;


    /**
     * Amount of bytes still available in a buffer.
     */
    int available();


    /**
     * Has the request body been read fully?
     */
    boolean isFinished();
}
