package com.gqz.others;

/*
 * 可重入锁：锁可以延续使用
 */
public class ReentrantLockTest01 {
    public static void main(String[] args) {
        new ReentrantLockTest01().test();
    }

    public void test() {
        //第一次获得锁
        synchronized (this) {
            while (true) {
                //第二次获得同样的锁
                synchronized (this) {
                    //如果锁是可重入的，则输出
                    System.out.println("ReentrantLock！。。。");
                }
                //休眠1秒
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
