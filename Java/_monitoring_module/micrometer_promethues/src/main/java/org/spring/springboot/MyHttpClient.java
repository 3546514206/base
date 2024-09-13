package org.spring.springboot;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyHttpClient {

    private static final String NORMAL_URL = "http://127.0.0.1:8089/app/user/normal";
    private static final String SUMMARY_URL = "http://127.0.0.1:8089/app/user/summary";

    private static final int THREAD_POOL_SIZE = 8;  // Size of the thread pool

    public static void main(String[] args) {
        // Create HttpClient instance
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Create a thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // Keep sending requests using the thread pool
        while (true) {
            try {
                // Submit tasks to the thread pool
                executorService.submit(() -> sendRequest(httpClient, NORMAL_URL));
                executorService.submit(() -> sendRequest(httpClient, SUMMARY_URL));

                // Sleep for a while to prevent too fast requests (1 second)
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendRequest(CloseableHttpClient httpClient, String url) {
        try {
            // Create a GET request
            HttpGet request = new HttpGet(url);

            // Execute the request and get the response
            HttpResponse response = httpClient.execute(request);

            // Read the response content
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Print the response content
            System.out.println("Response from " + url + ": " + result);

        } catch (Exception e) {
            System.err.println("Error while sending request to " + url + ": " + e.getMessage());
        }
    }
}
