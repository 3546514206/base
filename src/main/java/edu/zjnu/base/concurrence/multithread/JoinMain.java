package edu.zjnu.base.concurrence.multithread;

/**
 * @description: join()方法要做的事就是，当有新的线程加入时，主线程会进入等待状态，
 * 一直到调用join()方法的线程执行结束为止。
 * @author: 杨海波
 * @date: 2022-07-25 09:42
 **/
public class JoinMain {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + "子线程执行");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable, "子线程1");
        Thread thread2 = new Thread(runnable, "子线程2");

        // 调用start方法但是调用该方法只是准备线程并不是马上启动
        thread1.start();
        thread2.start();
        // 只有当 start() 启动后 才能正确执行 join()
        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread().getName() + ":" + "主线程执行");
    }
}
