package edu.zjnu;

import edu.zjnu.corn.Downloader;

import java.util.Scanner;

/**
 * @author: 杨海波
 * @date: 2023-11-07 23:09:23
 * @description: 下载器主类
 */
public class Main {

    public static void main(String[] args) {
        String url = null;

        // 拿到下载链接
        if (args == null || args.length == 0) {
            for (; ; ) {
                System.out.println("请输入下载链接");
                Scanner scanner = new Scanner(System.in);

                url = scanner.next();
                if (url != null) {
                    // 如果用户输入了。则退出阻塞循环
                    break;
                }
            }
        } else {
            url = args[0];
            checkUrl(url);
        }

        doDownloader(url);
    }

    private static void doDownloader(String url) {
        Downloader downloader = new Downloader();
        downloader.download(url);
    }

    // 校验地址
    private static void checkUrl(String url) {
        // todo
    }
}
