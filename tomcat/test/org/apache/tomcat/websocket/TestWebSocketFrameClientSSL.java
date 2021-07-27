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
package org.apache.tomcat.websocket;

import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.net.TesterSupport;
import org.apache.tomcat.websocket.TesterMessageCountClient.BasicText;
import org.apache.tomcat.websocket.TesterMessageCountClient.SleepingText;
import org.apache.tomcat.websocket.TesterMessageCountClient.TesterProgrammaticEndpoint;
import org.junit.Assert;
import org.junit.Test;

import javax.websocket.*;
import java.net.URI;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestWebSocketFrameClientSSL extends TomcatBaseTest {

    @Test
    public void testConnectToServerEndpoint() throws Exception {
        Tomcat tomcat = getTomcatInstance();
        // No file system docBase required
        Context ctx = tomcat.addContext("", null);
        ctx.addApplicationListener(TesterFirehoseServer.Config.class.getName());
        Tomcat.addServlet(ctx, "default", new DefaultServlet());
        ctx.addServletMapping("/", "default");

        TesterSupport.initSsl(tomcat);

        tomcat.start();

        WebSocketContainer wsContainer =
                ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig clientEndpointConfig =
                ClientEndpointConfig.Builder.create().build();
        clientEndpointConfig.getUserProperties().put(
                Constants.SSL_TRUSTSTORE_PROPERTY,
                "test/org/apache/tomcat/util/net/ca.jks");
        Session wsSession = wsContainer.connectToServer(
                TesterProgrammaticEndpoint.class,
                clientEndpointConfig,
                new URI("wss://localhost:" + getPort() +
                        TesterFirehoseServer.Config.PATH));
        CountDownLatch latch =
                new CountDownLatch(TesterFirehoseServer.MESSAGE_COUNT);
        BasicText handler = new BasicText(latch);
        wsSession.addMessageHandler(handler);
        wsSession.getBasicRemote().sendText("Hello");

        System.out.println("Sent Hello message, waiting for data");

        // Ignore the latch result as the message count test below will tell us
        // if the right number of messages arrived
        handler.getLatch().await(TesterFirehoseServer.WAIT_TIME_MILLIS,
                TimeUnit.MILLISECONDS);

        Queue<String> messages = handler.getMessages();
        Assert.assertEquals(
                TesterFirehoseServer.MESSAGE_COUNT, messages.size());
        for (String message : messages) {
            Assert.assertEquals(TesterFirehoseServer.MESSAGE, message);
        }
    }


    @Test
    public void testBug56032() throws Exception {
        Tomcat tomcat = getTomcatInstance();
        // No file system docBase required
        Context ctx = tomcat.addContext("", null);
        ctx.addApplicationListener(TesterFirehoseServer.Config.class.getName());
        Tomcat.addServlet(ctx, "default", new DefaultServlet());
        ctx.addServletMapping("/", "default");

        TesterSupport.initSsl(tomcat);

        tomcat.start();

        WebSocketContainer wsContainer =
                ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig clientEndpointConfig =
                ClientEndpointConfig.Builder.create().build();
        clientEndpointConfig.getUserProperties().put(
                Constants.SSL_TRUSTSTORE_PROPERTY,
                "test/org/apache/tomcat/util/net/ca.jks");
        Session wsSession = wsContainer.connectToServer(
                TesterProgrammaticEndpoint.class,
                clientEndpointConfig,
                new URI("wss://localhost:" + getPort() +
                        TesterFirehoseServer.Config.PATH));

        // Process incoming messages very slowly
        MessageHandler handler = new SleepingText(5000);
        wsSession.addMessageHandler(handler);
        wsSession.getBasicRemote().sendText("Hello");

        // Wait long enough for the buffers to fill and the send to timeout
        int count = 0;
        int limit = TesterFirehoseServer.WAIT_TIME_MILLIS / 100;

        System.out.println("Waiting for server to report an error");
        while (TesterFirehoseServer.Endpoint.getErrorCount() == 0 && count < limit) {
            Thread.sleep(100);
            count ++;
        }

        if (TesterFirehoseServer.Endpoint.getErrorCount() == 0) {
            Assert.fail("No error reported by Endpoint when timeout was expected");
        }

        // Wait up to another 10 seconds for the connection to be closed -
        // should be a lot faster.
        System.out.println("Waiting for connection to be closed");
        count = 0;
        limit = (TesterFirehoseServer.SEND_TIME_OUT_MILLIS * 2) / 100;
        while (TesterFirehoseServer.Endpoint.getOpenConnectionCount() != 0 && count < limit) {
            Thread.sleep(100);
            count ++;
        }

        int openConnectionCount = TesterFirehoseServer.Endpoint.getOpenConnectionCount();
        if (openConnectionCount != 0) {
            Assert.fail("There are [" + openConnectionCount + "] connections still open");
        }

        // Close the client session.
        wsSession.close();

        // Make sure the background process has stopped (else in some test
        // environments it will continue to run and break other tests that check
        // it has stopped.
        count = 0;
        while (count < 50) {
            if (BackgroundProcessManager.getInstance().getProcessCount() == 0) {
                break;
            }
            Thread.sleep(100);
            count++;
        }

        Assert.assertEquals(0, BackgroundProcessManager.getInstance().getProcessCount());

    }
}
