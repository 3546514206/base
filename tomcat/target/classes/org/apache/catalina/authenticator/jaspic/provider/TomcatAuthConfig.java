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
package org.apache.catalina.authenticator.jaspic.provider;

import org.apache.catalina.Context;
import org.apache.catalina.Realm;
import org.apache.catalina.authenticator.jaspic.provider.modules.BasicAuthModule;
import org.apache.catalina.authenticator.jaspic.provider.modules.DigestAuthModule;
import org.apache.catalina.authenticator.jaspic.provider.modules.FormAuthModule;
import org.apache.catalina.authenticator.jaspic.provider.modules.TomcatAuthModule;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.res.StringManager;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.MessageInfo;
import javax.security.auth.message.config.ServerAuthConfig;
import javax.security.auth.message.config.ServerAuthContext;
import java.util.HashMap;
import java.util.Map;

public class TomcatAuthConfig implements ServerAuthConfig {
    protected static final StringManager sm = StringManager.getManager(TomcatAuthConfig.class);

    private String messageLayer;
    private String appContext;
    private CallbackHandler handler;
    private TomcatServerAuthContext tomcatServerAuthContext;

    private Context context;
    private LoginConfig loginConfig;
    private Realm realm;


    public TomcatAuthConfig(String layer, String appContext, CallbackHandler callbackHandler,
            Context context) {
        this.messageLayer = layer;
        this.appContext = appContext;
        this.handler = callbackHandler;
        this.context = context;
        this.realm = context.getRealm();
        this.loginConfig = context.getLoginConfig();
    }


    @Override
    public String getMessageLayer() {
        return messageLayer;
    }


    @Override
    public String getAppContext() {
        return appContext;
    }


    @Override
    public String getAuthContextID(MessageInfo messageInfo) {
        return messageInfo.toString();
    }


    @Override
    public synchronized void refresh() {
        this.tomcatServerAuthContext = null;
    }


    @Override
    public boolean isProtected() {
        return false;
    }


    @Override
    @SuppressWarnings("rawtypes")
    public synchronized ServerAuthContext getAuthContext(String authContextID,
            Subject serviceSubject, Map properties) throws AuthException {
        if (this.tomcatServerAuthContext == null) {
            this.tomcatServerAuthContext = new TomcatServerAuthContext(handler, getModule(),
                    getOptions());
        }
        return tomcatServerAuthContext;
    }


    private Map<String, String> getOptions() {
        Map<String, String> options = new HashMap<>();
        options.put(TomcatAuthModule.REALM_NAME, getRealmName());
        return options;
    }


    private TomcatAuthModule getModule() throws AuthException {
        String authMethod = getAuthMethod();
        switch (authMethod) {
        case "BASIC": {
            return new BasicAuthModule(context);
        }
        case "DIGEST": {
            return new DigestAuthModule(context);
        }
        case "FORM": {
            return new FormAuthModule(context);
        }
        default: {
            throw new AuthException(
                    sm.getString("authenticator.jaspic.unknownAuthType", authMethod));
        }
        }
    }


    private String getRealmName() {
        return loginConfig.getRealmName();
    }


    /**
     * Temporary workaround to get authentication method
     * @return
     */
    private String getAuthMethod() {
        return loginConfig.getAuthMethod().replace("JASPIC-", "");
    }
}
