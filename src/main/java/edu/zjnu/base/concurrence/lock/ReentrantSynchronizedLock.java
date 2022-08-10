package edu.zjnu.base.concurrence.lock;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-05 09:07
 **/
public class ReentrantSynchronizedLock {


    public static void main(String[] args) {
        // f1 与 f2 都对当前类型的同一实例上锁，
        // 该上锁过程显然是可重入的否则在 "操作一" 的地方会被阻塞
        new ReentrantSynchronizedLock().f1();
    }

    public synchronized void f1() {
        f2();   // 操作一
        System.out.println("f1 执行完毕");
    }

    public synchronized void f2() {
        System.out.println("f2 执行完毕");
    }
}
