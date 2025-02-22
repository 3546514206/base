package com.gqz.state;

/**
 * @author ganquanzhong
 * @ClassName: YieldDemo01
 * @Description: yield
 * @date 2019年7月8日 下午5:01:13
 */
public class YieldDemo02 {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("lambda....." + i);
            }
        }).start();


        for (int i = 0; i < 100; i++) {
            if (i % 20 == 0) {
                Thread.yield();//main礼让
            }
            System.out.println("main...." + i);
        }
    }
}

