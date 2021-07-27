/*
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
package org.apache.catalina.authenticator.jaspic.provider.modules;

import org.apache.catalina.Context;
import org.apache.catalina.authenticator.jaspic.MessageInfoImpl;
import org.apache.tomcat.util.res.StringManager;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.MessageInfo;
import javax.security.auth.message.MessagePolicy;
import javax.security.auth.message.callback.CallerPrincipalCallback;
import javax.security.auth.message.callback.GroupPrincipalCallback;
import javax.security.auth.message.module.ServerAuthModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * Base class for JASPIC authentication modules.
 */
public abstract class TomcatAuthModule implements ServerAuthModule {

    public static final String REALM_NAME = "javax.servlet.http.realmName";
    public static final String DEFAULT_REALM_NAME = "Authentication required";

    protected static final String AUTH_HEADER_NAME = "WWW-Authenticate";
    protected static final String AUTHORIZATION_HEADER = "authorization";

    /**
     * The string manager for this package.
     */
    protected static final StringManager sm = StringManager.getManager(TomcatAuthModule.class);

    protected String realmName;

    protected CallbackHandler handler;

    protected Context context;

    protected boolean cachePrincipalsInSession = true;


    public TomcatAuthModule(Context context) {
        this.context = context;
    }


    protected boolean isMandatory(MessageInfo messageInfo) {
        String mandatory = (String) messageInfo.getMap().get(MessageInfoImpl.IS_MANDATORY);
        return Boolean.parseBoolean(mandatory);
    }


    @SuppressWarnings("rawtypes")
    @Override
    public final void initialize(MessagePolicy requestPolicy, MessagePolicy responsePolicy,
            CallbackHandler handler, Map options) throws AuthException {
        this.handler = handler;
        this.realmName = (String) options.get(REALM_NAME);
        initializeModule(requestPolicy, responsePolicy, handler, options);
    }


    public String getRealmName() {
        return Optional.ofNullable(realmName).orElse(DEFAULT_REALM_NAME);
    }


    /**
     * Every subclass must extend this method in order to be initialized.
     * Firstly, we initialize abstract module, then subclasses.
     *
     * @param requestPolicy
     * @param responsePolicy
     * @param handler
     * @param options
     * @throws AuthException
     */
    @SuppressWarnings("rawtypes")
    public abstract void initializeModule(MessagePolicy requestPolicy,
            MessagePolicy responsePolicy, CallbackHandler handler, Map options)
            throws AuthException;


    /**
     * Convert Tomcat's principal to JAAS subject using JASPIC callbacks
     *
     * @param clientSubject
     * @param principal
     * @throws IOException
     * @throws UnsupportedCallbackException
     */
    protected void handlePrincipalCallbacks(Subject clientSubject, Principal principal)
            throws IOException, UnsupportedCallbackException {
        CallerPrincipalCallback principalCallback = new CallerPrincipalCallback(clientSubject,
                principal);
        String[] roles = context.getRealm().getRoles(principal);
        GroupPrincipalCallback groupCallback = new GroupPrincipalCallback(clientSubject, roles);
        handler.handle(new Callback[] { principalCallback, groupCallback });
    }
}
