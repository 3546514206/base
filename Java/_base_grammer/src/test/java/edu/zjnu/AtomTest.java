package edu.zjnu;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 杨海波
 * @date 2024-03-11 12:44
 * @description AtomTest
 */
public class AtomTest {

    private static AtomicInteger sum = new AtomicInteger(0);

    public static synchronized void inCreate() {
        sum.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + ":" + sum);
    }

    public static void main(String[] args) {
        for (int thread = 0; thread < 2; thread++) {
            new Thread(() -> {
                for (int index = 0; index < 100; index++) {
                    inCreate();

                }
            }, "thread-" + thread).start();
        }
    }
}
