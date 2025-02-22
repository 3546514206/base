package com.gqz.state;

/*
 * join   合并线程 插队线程
 */
public class BlockedJoin01 {
    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("lambda....." + i);
            }
        });
        th.start();


        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                th.join();
                ;//main 线程阻塞
            }
            System.out.println("main...." + i);
        }
    }
}
