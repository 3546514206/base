package edu.zjnu.base.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: ReentrantReadWriteLockMain
 * @author: 杨海波
 * @date: 2022-08-08 10:31
 **/
public class ReentrantReadWriteLockMain {

    public static void main(String[] args) {
        LockTask task = new LockTask();

        Thread tr1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.read();
            }
        }, "tr1");

        Thread tr2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.read();
            }
        }, "tr2");

        Thread tr3 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.read();
            }
        }, "tr3");

        Thread tw1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.write();
            }
        }, "tw1");

        Thread tw2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.write();
            }
        }, "tw2");

        Thread tw3 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.write();
            }
        }, "tw3");

        try {
            tr1.start();
            tr2.start();
            tr3.start();
            tw1.start();
            tw2.start();
            tw3.start();
            tr1.join();
            tr2.join();
            tr3.join();
            tw1.join();
            tw2.join();
            tw3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

class LockTask {
    // private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " start reading!!!");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " end reading!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " start writing!!!");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " end writing!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}
