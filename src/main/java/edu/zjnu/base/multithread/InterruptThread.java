package edu.zjnu.base.multithread;

/**
 * @description: InterruptThread
 * @author: 杨海波
 * @date: 2022-01-17
 **/
public class InterruptThread extends Thread {

    @Override
    public void run() {
        // 不仅会读取中断标志位，还会重置标志位
        Thread.interrupted();
    }
}
