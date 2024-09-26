package edu.zjnu;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Java 虚拟线程
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 传统线程池基准测试
        long startTime = System.nanoTime();
        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 1_000_000; i++) {
            threadPool.submit(() -> {
                // 模拟任务
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        long endTime = System.nanoTime();
        System.out.println("传统线程池执行时间: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + " ms");


        // 虚拟线程基准测试（需要 Java 19+）
        startTime = System.nanoTime();
        ExecutorService virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 1_000_000; i++) {
            virtualThreadPool.submit(() -> {
                // 模拟任务
            });
        }
        virtualThreadPool.shutdown();
        virtualThreadPool.awaitTermination(1, TimeUnit.HOURS);
        endTime = System.nanoTime();
        System.out.println("虚拟线程池执行时间: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + " ms");
    }
}