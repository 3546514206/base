package edu.zjnu.base.multithread;

/**
 * @description: threadLocalMain
 * @author: 杨海波
 * @date: 2022-07-08 10:21
 **/
public class threadLocalMain {

    public static void main(String[] args) {

        Thread threadLocalTestThread1 = new Thread(new ThreadLocalTestThread("thread-1-string"));
        Thread threadLocalTestThread2 = new Thread(new ThreadLocalTestThread("thread-2-string"));
        threadLocalTestThread1.start();
        threadLocalTestThread2.start();
    }

    private static class ThreadLocalTestThread implements Runnable{

        private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

        private String arg;

        public ThreadLocalTestThread(String arg) {
            this.arg = arg;
        }

        @Override
        public void run() {
            threadLocal.set(this.arg);
            doBiz();
            System.out.println(threadLocal.get());
        }

        private void doBiz() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
