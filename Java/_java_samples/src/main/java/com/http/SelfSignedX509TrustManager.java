package com.http;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author landyl
 * @create 10:41 AM 08/14/2018
 */
public class SelfSignedX509TrustManager implements X509TrustManager {
    public SelfSignedX509TrustManager() {
    }

    public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
