package edu.zjnu;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * Java 虚拟线程
 */
public class Main {
    public static void main(String[] args) {
        final int NUMBER_OF_THREADS = 10;
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Thread.ofVirtual().start(() -> {
                try {
                    sendHttpRequest("https://www.baidu.com/");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await(); // 等待所有线程完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("所有请求已完成。");
    }

    private static void sendHttpRequest(String targetUrl) throws Exception {
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("响应代码: " + responseCode);
        connection.disconnect();
    }
}