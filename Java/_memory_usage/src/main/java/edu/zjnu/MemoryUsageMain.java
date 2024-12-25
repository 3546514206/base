package edu.zjnu;

/**
 * @author: 杨海波
 * @date: 2023-11-24 17:10:23
 * @description: MemoryUsageMain
 */
public class MemoryUsageMain {

    public static void main(String[] args) {
        // 开始时打印当前堆内存信息
        printMemoryUsage("Initial");

        // 创建一些对象以模拟堆内存的使用
        for (int i = 0; i < 10000; i++) {
            String temp = new String("Object" + i);
        }

        // 执行垃圾回收
        System.gc();

        // 打印垃圾回收后的堆内存信息
        printMemoryUsage("After GC");
    }

    private static void printMemoryUsage(String stage) {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();

        System.out.println("Memory Usage at " + stage + ":");
        System.out.println("Free Memory: " + (freeMemory / (1024 * 1024)) + " MB");
        System.out.println("Total Memory: " + (totalMemory / (1024 * 1024)) + " MB");
        System.out.println("Max Memory: " + (maxMemory / (1024 * 1024)) + " MB");
        System.out.println("----------------------------------------");
    }
}
