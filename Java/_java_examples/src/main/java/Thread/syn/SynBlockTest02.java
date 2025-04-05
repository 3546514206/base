package com.gqz.syn;

import java.util.ArrayList;
import java.util.List;

public class SynBlockTest02 {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 10000; i++) {
            // 代码同步块
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(list.size());
    }
}
