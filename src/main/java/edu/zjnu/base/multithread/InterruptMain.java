package edu.zjnu.base.multithread;

/**
 * @description: Interrupt
 * @author: 杨海波
 * @date: 2022-01-17
 **/
public class InterruptMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new InterruptThread();
        thread.start();

        Thread.sleep(1000);
        // 只是读取中断标志位
        thread.isInterrupted();
    }
}
