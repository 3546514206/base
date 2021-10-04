package edu.zjnu.base.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: SynchronizedMain
 * @author: 杨海波
 * @date: 2021-10-04
 **/
public class SynchronizedMain {

    private static Object monitor = new Object();

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        for (long i = 0; i < 100; i++) {
            new Thread(() -> {
                final ReentrantLock lock = new ReentrantLock();
                lock.lock();
                try {
                    list.add("hello");
                    list.add("java");
                    list.add("!");
                } finally {
                    lock.unlock();
                }

            }).start();
        }

    }

}
