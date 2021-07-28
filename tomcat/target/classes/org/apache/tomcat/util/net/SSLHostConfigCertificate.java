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
package org.apache.tomcat.util.net;

import org.apache.tomcat.util.net.AbstractJsseEndpoint.SSLContextWrapper;
import org.apache.tomcat.util.net.jsse.openssl.Authentication;

import java.util.HashSet;
import java.util.Set;


public class SSLHostConfigCertificate {

    public static final Type DEFAULT_TYPE = Type.UNDEFINED;

    static final String DEFAULT_KEYSTORE_PROVIDER =
            System.getProperty("javax.net.ssl.keyStoreProvider");
    static final String DEFAULT_KEYSTORE_TYPE =
            System.getProperty("javax.net.ssl.keyStoreType", "JKS");

    // OpenSSL can handle multiple certs in a single config so the reference to
    // the context is at the virtual host level. JSSE can't so the reference is
    // held here on the certificate.
    private SSLContextWrapper sslContextWrapper;

    // Common
    private final SSLHostConfig sslHostConfig;
    private final Type type;
    private String certificateKeyPassword = null;

    // JSSE
    private String certificateKeyAlias;
    private String certificateKeystorePassword = "changeit";
    private String certificateKeystoreFile = System.getProperty("user.home")+"/.keystore";
    private String certificateKeystoreProvider = DEFAULT_KEYSTORE_PROVIDER;
    private String certificateKeystoreType = DEFAULT_KEYSTORE_TYPE;

    // OpenSSL
    private String certificateFile;
    private String certificateKeyFile;


    public SSLHostConfigCertificate() {
        this(null, Type.UNDEFINED);
    }

    public SSLHostConfigCertificate(SSLHostConfig sslHostConfig, Type type) {
        this.sslHostConfig = sslHostConfig;
        this.type = type;
    }


    public SSLContextWrapper getSslContextWrapper() {
        return sslContextWrapper;
    }


    public void setSslContextWrapper(SSLContextWrapper sslContextWrapper) {
        this.sslContextWrapper = sslContextWrapper;
    }


    // Common

    public Type getType() {
        return type;
    }


    public String getCertificateKeyPassword() {
        return certificateKeyPassword;
    }


    public void setCertificateKeyPassword(String certificateKeyPassword) {
        this.certificateKeyPassword = certificateKeyPassword;
    }


    // JSSE

    public void setCertificateKeyAlias(String certificateKeyAlias) {
        sslHostConfig.setProperty(
                "Certificate.certificateKeyAlias", SSLHostConfig.Type.JSSE);
        this.certificateKeyAlias = certificateKeyAlias;
    }


    public String getCertificateKeyAlias() {
        return certificateKeyAlias;
    }


    public void setCertificateKeystoreFile(String certificateKeystoreFile) {
        sslHostConfig.setProperty(
                "Certificate.certificateKeystoreFile", SSLHostConfig.Type.JSSE);
        this.certificateKeystoreFile = certificateKeystoreFile;
    }


    public String getCertificateKeystoreFile() {
        return certificateKeystoreFile;
    }


    public void setCertificateKeystorePassword(String certificateKeystorePassword) {
        sslHostConfig.setProperty(
                "Certificate.certificateKeystorePassword", SSLHostConfig.Type.JSSE);
        this.certificateKeystorePassword = certificateKeystorePassword;
    }


    public String getCertificateKeystorePassword() {
        return certificateKeystorePassword;
    }


    public void setCertificateKeystoreProvider(String certificateKeystoreProvider) {
        sslHostConfig.setProperty(
                "Certificate.certificateKeystoreProvider", SSLHostConfig.Type.JSSE);
        this.certificateKeystoreProvider = certificateKeystoreProvider;
    }


    public String getCertificateKeystoreProvider() {
        return certificateKeystoreProvider;
    }


    public void setCertificateKeystoreType(String certificateKeystoreType) {
        sslHostConfig.setProperty(
                "Certificate.certificateKeystoreType", SSLHostConfig.Type.JSSE);
        this.certificateKeystoreType = certificateKeystoreType;
    }


    public String getCertificateKeystoreType() {
        return certificateKeystoreType;
    }


    // OpenSSL

    public void setCertificateFile(String certificateFile) {
        sslHostConfig.setProperty(
                "Certificate.certificateFile", SSLHostConfig.Type.OPENSSL);
        this.certificateFile = certificateFile;
    }


    public String getCertificateFile() {
        return certificateFile;
    }


    public void setCertificateKeyFile(String certificateKeyFile) {
        sslHostConfig.setProperty(
                "Certificate.certificateKeyFile", SSLHostConfig.Type.OPENSSL);
        this.certificateKeyFile = certificateKeyFile;
    }


    public String getCertificateKeyFile() {
        return certificateKeyFile;
    }


    // Nested types

    public enum Type {

        UNDEFINED,
        RSA(Authentication.RSA),
        DSA(Authentication.DSS),
        EC(Authentication.ECDH, Authentication.ECDSA);

        private final Set<Authentication> compatibleAuthentications;

        Type(Authentication... authentications) {
            compatibleAuthentications = new HashSet<>();
            if (authentications != null) {
                for (Authentication authentication : authentications) {
                    compatibleAuthentications.add(authentication);
                }
            }
        }

        public boolean isCompatibleWith(Authentication au) {
            return compatibleAuthentications.contains(au);
        }
    }
}
