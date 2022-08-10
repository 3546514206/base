package edu.zjnu.base.concurrence.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: happen-before 的传递性
 * @author: 杨海波
 * @date: 2022-08-04 17:55
 **/
public class PassHappenBeforeMain {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static A a = new A();

    private static Object monitor = new Object();

    public static int index;

    public static void main(String[] args) {
        synchronized (monitor){

        }
    }
}

class A {
    private int a = 0;
    private volatile int c = 0;

    public void set(int a) {
        this.a = a;
        this.c = 1;
    }

    public int get() {
        int d = c;
        return this.a;
    }
}

class GetThread implements Runnable {

    @Override
    public void run() {
        System.out.println(PassHappenBeforeMain.a.get());
    }
}

class SetThread implements Runnable {

    @Override
    public void run() {
        System.out.println(PassHappenBeforeMain.index);
        PassHappenBeforeMain.a.set(PassHappenBeforeMain.index);
    }
}

