package edu.zjnu.base.concurrence.multithread;

import java.util.concurrent.Semaphore;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-10 16:11
 **/


public class PrintQueue {

    private final Semaphore semaphore;

    PrintQueue() {
        // 只设定一个许可,这样同一个时刻 只能一个线程执行 printJob方法,从而实现互斥锁
        this.semaphore = new Semaphore(1);
    }

    //2、此方法可以模拟打印文档，并接收 document 对象作为参数。
    public void printJob(Object document) {
        try {
            semaphore.acquire();
            //3.然后，实现能随机等待一段时间的模拟打印文档的行。
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //7.最后，释放semaphore通过调用semaphore的release()方法。
            semaphore.release();
            System.out.printf("%s: PrintQueue: Printing a Job release the permits \n", Thread.currentThread().getName());
        }
    }
}
