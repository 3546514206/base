package edu.zjnu.base.concurrence.multithread.commu;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 杨海波
 * @date: 2022-07-05 09:44
 **/
public class LockObjectMain {

    public static void main(String[] args) {
        Object lock = new Object();
        List<String> list = new ArrayList<>();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 20; i++) {
                    synchronized (lock) {
                        list.add("order:" + i);
                        System.out.println("线程1 向 list 添加元素，此时的元素个数：" + list.size());

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 当元素个数为16的时候，通知线程2执行相关操作
                        if (list.size() == 5) {
                            lock.notify();
                        }
                    }
                }
            }
        }, "thread-00001");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (true) {
                        if (list.size() != 5) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "thread-00002");

        thread2.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
    }
}
