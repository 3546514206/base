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
package org.apache.coyote.ajp;

import org.apache.coyote.AbstractProtocol;
import org.apache.coyote.UpgradeProtocol;
import org.apache.tomcat.util.net.AbstractEndpoint;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SocketWrapperBase;
import org.apache.tomcat.util.res.StringManager;

import javax.servlet.http.HttpUpgradeHandler;
import java.nio.ByteBuffer;

/**
 * The is the base implementation for the AJP protocol handlers. Implementations
 * typically extend this base class rather than implement {@link
 * org.apache.coyote.ProtocolHandler}. All of the implementations that ship with
 * Tomcat are implemented this way.
 *
 * @param <S> The type of socket used by the implementation
 */
public abstract class AbstractAjpProtocol<S> extends AbstractProtocol<S> {

    /**
     * The string manager for this package.
     */
    protected static final StringManager sm = StringManager.getManager(AbstractAjpProtocol.class);
    /**
     * Should authentication be done in the native web server layer,
     * or in the Servlet container ?
     */
    private boolean tomcatAuthentication = true;
    /**
     * Should authentication be done in the native web server layer and
     * authorization in the Servlet container?
     */
    private boolean tomcatAuthorization = false;
    /**
     * Required secret.
     */
    private String requiredSecret = null;
    /**
     * AJP packet size.
     */
    private int packetSize = Constants.MAX_PACKET_SIZE;


    // ------------------------------------------------- AJP specific properties
    // ------------------------------------------ managed in the ProtocolHandler

    public AbstractAjpProtocol(AbstractEndpoint<S> endpoint) {
        super(endpoint);
        setSoTimeout(Constants.DEFAULT_CONNECTION_TIMEOUT);
        // AJP does not use Send File
        getEndpoint().setUseSendfile(false);
    }

    @Override
    protected String getProtocolName() {
        return "Ajp";
    }

    /**
     * {@inheritDoc}
     *
     * Overridden to make getter accessible to other classes in this package.
     */
    @Override
    protected AbstractEndpoint<S> getEndpoint() {
        return super.getEndpoint();
    }

    /**
     * {@inheritDoc}
     *
     * AJP does not support protocol negotiation so this always returns null.
     */
    @Override
    protected UpgradeProtocol getNegotiatedProtocol(String name) {
        return null;
    }

    public boolean getTomcatAuthentication() { return tomcatAuthentication; }

    public void setTomcatAuthentication(boolean tomcatAuthentication) {
        this.tomcatAuthentication = tomcatAuthentication;
    }

    public boolean getTomcatAuthorization() { return tomcatAuthorization; }

    public void setTomcatAuthorization(boolean tomcatAuthorization) {
        this.tomcatAuthorization = tomcatAuthorization;
    }

    public void setRequiredSecret(String requiredSecret) {
        this.requiredSecret = requiredSecret;
    }

    public int getPacketSize() { return packetSize; }
    public void setPacketSize(int packetSize) {
        if(packetSize < Constants.MAX_PACKET_SIZE) {
            this.packetSize = Constants.MAX_PACKET_SIZE;
        } else {
            this.packetSize = packetSize;
        }
    }


    // --------------------------------------------- SSL is not supported in AJP

    @Override
    public void addSslHostConfig(SSLHostConfig sslHostConfig) {
        getLog().warn(sm.getString("ajpprotocol.noSSL", sslHostConfig.getHostName()));
    }


    @Override
    public SSLHostConfig[] findSslHostConfigs() {
        return new SSLHostConfig[0];
    }


    @Override
    public void addUpgradeProtocol(UpgradeProtocol upgradeProtocol) {
        getLog().warn(sm.getString("ajpprotocol.noUpgrade", upgradeProtocol.getClass().getName()));
    }


    @Override
    public UpgradeProtocol[] findUpgradeProtocols() {
        return new UpgradeProtocol[0];
    }


    protected void configureProcessor(AjpProcessor processor) {
        processor.setAdapter(getAdapter());
        processor.setTomcatAuthentication(getTomcatAuthentication());
        processor.setTomcatAuthorization(getTomcatAuthorization());
        processor.setRequiredSecret(requiredSecret);
        processor.setKeepAliveTimeout(getKeepAliveTimeout());
        processor.setClientCertProvider(getClientCertProvider());
    }

    protected abstract static class AbstractAjpConnectionHandler<S>
            extends AbstractConnectionHandler<S,AjpProcessor> {

        private final AbstractAjpProtocol<S> proto;

        public AbstractAjpConnectionHandler(AbstractAjpProtocol<S> proto) {
            this.proto = proto;
        }

        @Override
        protected AbstractAjpProtocol<S> getProtocol() {
            return proto;
        }


        @Override
        protected AjpProcessor createProcessor() {
            AjpProcessor processor = new AjpProcessor(proto.getPacketSize(), proto.getEndpoint());
            proto.configureProcessor(processor);
            register(processor);
            return processor;
        }


        @Override
        protected AjpProcessor createUpgradeProcessor(SocketWrapperBase<?> socket,
                ByteBuffer leftoverInput, HttpUpgradeHandler httpUpgradeHandler) {
            // TODO should fail - throw IOE
            return null;
        }
    }
}
