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
import org.apache.tomcat.websocket.TesterMessageCountClient.BasicText;
import org.apache.tomcat.websocket.TesterMessageCountClient.TesterProgrammaticEndpoint;
import org.junit.Assert;
import org.junit.Test;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestWebSocketFrameClient extends TomcatBaseTest {

    @Test
    public void testConnectToServerEndpoint() throws Exception {

        Tomcat tomcat = getTomcatInstance();
        // No file system docBase required
        Context ctx = tomcat.addContext("", null);
        ctx.addApplicationListener(TesterFirehoseServer.Config.class.getName());
        Tomcat.addServlet(ctx, "default", new DefaultServlet());
        ctx.addServletMapping("/", "default");

        tomcat.start();

        WebSocketContainer wsContainer =
                ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig clientEndpointConfig =
                ClientEndpointConfig.Builder.create().build();
        Session wsSession = wsContainer.connectToServer(
                TesterProgrammaticEndpoint.class,
                clientEndpointConfig,
                new URI("ws://localhost:" + getPort() +
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
    public void testConnectToRootEndpoint() throws Exception {

        Tomcat tomcat = getTomcatInstance();
        // No file system docBase required
        Context ctx = tomcat.addContext("", null);
        ctx.addApplicationListener(TesterEchoServer.Config.class.getName());
        Tomcat.addServlet(ctx, "default", new DefaultServlet());
        ctx.addServletMapping("/", "default");
        Context ctx2 = tomcat.addContext("/foo", null);
        ctx2.addApplicationListener(TesterEchoServer.Config.class.getName());
        Tomcat.addServlet(ctx2, "default", new DefaultServlet());
        ctx2.addServletMapping("/", "default");

        tomcat.start();

        echoTester("");
        echoTester("/");
        // FIXME: The ws client doesn't handle any response other than the upgrade,
        // which may or may not be allowed. In that case, the server will return
        // a redirect to the root of the webapp to avoid possible broken relative
        // paths.
        // echoTester("/foo");
        echoTester("/foo/");
    }

    public void echoTester(String path) throws Exception {
        WebSocketContainer wsContainer =
                ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig clientEndpointConfig =
                ClientEndpointConfig.Builder.create().build();
        Session wsSession = wsContainer.connectToServer(
                TesterProgrammaticEndpoint.class,
                clientEndpointConfig,
                new URI("ws://localhost:" + getPort() + path));
        CountDownLatch latch =
                new CountDownLatch(1);
        BasicText handler = new BasicText(latch);
        wsSession.addMessageHandler(handler);
        wsSession.getBasicRemote().sendText("Hello");

        boolean latchResult = handler.getLatch().await(10, TimeUnit.SECONDS);
        Assert.assertTrue(latchResult);

        Queue<String> messages = handler.getMessages();
        Assert.assertEquals(1, messages.size());
        for (String message : messages) {
            Assert.assertEquals("Hello", message);
        }
        wsSession.close();
    }

}
