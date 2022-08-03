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
 * 4) 这种写法只适合生产者和消费者一对一的情况。如果存在多个生产者或者多个消费者：生产者本来只想通知消费者，但它把
 * 其他的生产者也通知了；消费者本来只想通知生产者，但它被其他的消费者通知了。原因就是wait（）和notify（）所作用的
 * 对象和synchronized所作用的对象是同一个。这正是Condition要解决的问题。
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
                        // wait() 的时候必须释放锁!!!当然，这是 JVM 做的，不需要用户自己做
                        // 否则 producer 线程将永远无法获取锁，从而得到执行权限。
                        // wait(){
                        // 1) 释放当前线程持有的锁
                        // 2) 阻塞，等待被其他线程notify
                        // 3) 重新获取锁
                        // }
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
