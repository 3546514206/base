package edu.zjnu.base.concurrence;

import java.util.concurrent.TimeUnit;

/**
 * @description: WaitNotifyMain
 * <p>
 * 1) 在示例中没有体现但很重要的是，wait/notify方法的调用必须处在该对象的锁（Monitor）中，也即，在
 * 调用这些方法时首先需要获得该对象的锁。否则会抛出IllegalMonitorStateException异常。
 * 2) 从输出结果来看，在生产者调用notify()后，消费者并没有立即被唤醒，而是等到生产者退出同步块后才唤醒
 * 执行。（这点其实也好理解，synchronized加锁对象同一时刻只允许一个线程持有锁，生产者不退出，消费者也进不去）
 * 3) 注意，消费者被唤醒后是从wait()方法（被阻塞的地方）后面执行，而不是重新从同步块开始。
 * @author: 杨海波
 * @date: 2022-08-03 15:34
 **/
public class WaitNotifyMain {

    private static final Object monitor = new Object();

    private static boolean produced = false;

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(new Producer(), "producer");
        Thread consumer = new Thread(new Consumer(), "consumer");
        consumer.start();
        Thread.sleep(1000);
        producer.start();

        // t.join()方法阻塞调用此方法的线程(calling thread)，直到线程t完成，此线程再继续；
        // 通常用于在main()主线程内，等待其它线程完成再结束main()主线程。
        producer.join();
        consumer.join();
    }

    private static class Producer implements Runnable {

        @Override
        public void run() {
            synchronized (monitor) {
                try {
                    System.out.println("生产者线程开始生产");
                    // 模拟生产过程
                    TimeUnit.MILLISECONDS.sleep(5000);
                    produced = true;
                    monitor.notify();
                    // 模拟其他耗时操作
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println("生产者线程结束生产");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            synchronized (monitor) {
                System.out.println(monitor);
                while (!produced) {
                    try {
                        System.out.println("生产者线程未生产，进入等待");
                        monitor.wait();
                        System.out.println("结束等待");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费者线程开始消费");
                System.out.println("消费者线程结束消费");
            }
        }
    }
}
