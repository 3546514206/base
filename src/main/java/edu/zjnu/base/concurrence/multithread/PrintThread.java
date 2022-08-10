package edu.zjnu.base.concurrence.multithread;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-10 16:12
 **/
public class PrintThread extends Thread {

    private PrintQueue printQueue;

    public PrintThread(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        printQueue.printJob(new Object());
    }
}