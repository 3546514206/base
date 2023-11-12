package edu.zjnu.corn;

import edu.zjnu.constant.Constant;
import edu.zjnu.util.HttpUtils;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * @author: 杨海波
 * @date: 2023-11-11 20:09:03
 * @description: 下载器
 */
public class Downloader {

    /**
     * 下载文件
     *
     * @param url
     */
    public void download(String url) {

        String httpFileName = HttpUtils.getHttpFileName(url);
        httpFileName = Constant.WORK_SPACE_PATH + httpFileName;
  
        // 获取 httpConnection
        HttpURLConnection httpConnection = null;
        try {
            httpConnection = HttpUtils.getHttpURLConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 利用 io 流下载文件
        try (InputStream inputStream =  httpConnection.getInputStream();
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(httpFileName);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            int len = -1;
            while ((len = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }
}
