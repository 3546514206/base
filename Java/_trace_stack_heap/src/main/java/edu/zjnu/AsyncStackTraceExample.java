package edu.zjnu;

import java.util.concurrent.CompletableFuture;

/**
 * @author: 杨海波
 * @date: 2023-12-15 23:42:20
 * @description: 异步堆栈跟踪的 API
 */
public class AsyncStackTraceExample {
    public static void main(String[] args) {
        // 获取当前线程
        Thread thread = Thread.currentThread();

        // 获取堆栈跟踪
        CompletableFuture<String[]> stackTrace = thread.asyncGetStackTrace();

        // 等待堆栈跟踪可用
        String[] stackTraceArray = stackTrace.get();

        // 打印堆栈跟踪
        for (String stackFrame : stackTraceArray) {
            System.out.println(stackFrame);
        }
    }

}
