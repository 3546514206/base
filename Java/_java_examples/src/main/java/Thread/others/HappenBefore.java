package com.gqz.others;

public class HappenBefore {
    // 1、变量2
    private static int a = 0;
    // 2、变量2
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            a = 0;
            flag = false;

            // 线程1 更改数据
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;

            });

            // 线程2 更改数据
            Thread t2 = new Thread(() -> {
                if (flag) {
                    a *= 1;
                    System.out.println(a);
                }
                // 指定重排
                if (a == 0) {
                    System.out.println("happen before a--->" + a);
                }
            });

            t1.start();
            t2.start();

            // 合并线程
            t1.join();
            t2.join();
        }

    }
}
