package com.gqz.others;

/*
 * volatile 用于保证数据的同步，也就是可见性
 */
public class VolatileTest {
    private static volatile int num = 0; //保证数据的可见性

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0) {
                //此处不写代码
            }
        }).start();
        ;

        Thread.sleep(1000);
        num = 1;
    }
}
