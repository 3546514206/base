package edu.zjnu.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: 杨海波
 * @date: 2023-11-07 23:59:29
 * @description: Http 相关工具类
 */
public class HttpUtils {

    // 获取连接对象
    public static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        URLConnection connection = httpUrl.openConnection();
        // 设置客户端标识，模拟浏览器请求
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36" +
                " (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        return (HttpURLConnection) connection;
    }

    public static String getHttpFileName(String url) {
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }

}
