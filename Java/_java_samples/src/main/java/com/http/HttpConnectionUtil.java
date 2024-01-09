package com.http;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @author landyl
 * @create 10:39 AM 08/14/2018
 */
public class HttpConnectionUtil {
    public static HttpURLConnection getConnection(String method, String fullUrl) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        URL url = new URL(fullUrl);
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier((urlHostName, session) -> true);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        return connection;
    }

    private static void trustAllHttpsCertificates() throws KeyManagementException, NoSuchAlgorithmException {
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, new javax.net.ssl.TrustManager[]{new SelfSignedX509TrustManager()}, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
}
