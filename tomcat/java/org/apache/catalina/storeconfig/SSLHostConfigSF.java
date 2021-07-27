/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.catalina.storeconfig;

import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;

import java.io.PrintWriter;

/**
 * Store SSLHostConfig
 */
public class SSLHostConfigSF extends StoreFactoryBase {

    /**
     * Store SSLHostConfig description
     *
     * @param aWriter
     * @param indent
     * @param aSSLHostConfig
     * @throws Exception
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aSSLHostConfig,
            StoreDescription parentDesc) throws Exception {
        if (aSSLHostConfig instanceof SSLHostConfig) {
            SSLHostConfig sslHostConfig = (SSLHostConfig) aSSLHostConfig;
            // Store nested <SSLHostConfigCertificate> elements
            SSLHostConfigCertificate[] hostConfigsCertificates = sslHostConfig.getCertificates().toArray(new SSLHostConfigCertificate[0]);
            storeElementArray(aWriter, indent, hostConfigsCertificates);
        }
    }

}