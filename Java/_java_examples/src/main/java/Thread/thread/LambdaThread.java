package com.gqz.thread;

/**
 * Lambda 表达式 简化（用一次）线程的使用
 *
 * @author ganquanzhong
 * @ClassName: StartThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:41:47
 */
public class LambdaThread {

    public static void main(String[] args) {
//		new Thread(new Test()).start();;

        // 方法内部的 局部内部类
        class Test2 implements Runnable {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("一边听歌");
                }
            }
        }
        new Thread(new Test2()).start();

        // 匿名内部类  必须借助接口或者父类
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("一边听歌");
                }
            }
        }).start();


        //jdk8 简化 Lambda
        /*
         * jdk8 会自动识别run方法并且是Runnable的实现
         */
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("一边听歌");
            }
        }
        ).start();

        // 调用run方法 ，普通调用 直接调用run（）方法，不是加到线程调度器，而是普通方法
        for (int i = 0; i < 20; i++) {
            System.out.println("一边coding");
        }
    }

    // 静态内部类
    static class Test implements Runnable {
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("一边听歌");
            }
        }
    }
}
