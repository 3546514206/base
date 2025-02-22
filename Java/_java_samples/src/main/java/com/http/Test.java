package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * @author landyl
 * @create 10:41 AM 08/14/2018
 */
public class Test {

    public static void main(String[] args) throws Exception{
        HttpURLConnection con = HttpConnectionUtil.getConnection("GET", "https://www.csdn.net/");
        System.out.println(readResponse(con));
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        String Response = "";
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            Response = responseBuffer.toString();
            in.close();
        }
        return Response;
    }


}
