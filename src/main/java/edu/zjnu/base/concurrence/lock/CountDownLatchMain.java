package edu.zjnu.base.concurrence.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @description: CountDownLatchMain
 * @author: 杨海波
 * @date: 2022-08-19 14:58
 **/
public class CountDownLatchMain {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Increment increment = new Increment(latch);
        Decrement decrement = new Decrement(latch);

        Thread incrementThread = new Thread(increment, "incrementThread");
        Thread decrementThread = new Thread(decrement, "decrementThread");

        incrementThread.start();
        decrementThread.start();
        incrementThread.join();
        decrementThread.join();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Decrement implements Runnable {

    CountDownLatch countDownLatch;

    public Decrement(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {

            for (long i = countDownLatch.getCount(); i > 0; i--) {
                Thread.sleep(1000);
                System.out.println("countdown");
                this.countDownLatch.countDown();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Increment implements Runnable {

    CountDownLatch countDownLatch;

    public Increment(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("await");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");
    }
}