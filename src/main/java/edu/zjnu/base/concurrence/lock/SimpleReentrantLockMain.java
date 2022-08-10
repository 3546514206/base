package edu.zjnu.base.concurrence.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: SimpleReentrantLockMain
 * @author: 杨海波
 * @date: 2022-08-05 16:54
 **/
public class SimpleReentrantLockMain {


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);

        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("get lock!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "thread-1");

        thread.start();
        //thread.join();  // 操作一
    }
}
