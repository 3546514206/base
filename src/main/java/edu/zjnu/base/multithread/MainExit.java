package edu.zjnu.base.multithread;

/**
 * @description: 主线程退出了子线程会退出么:main线程退出之后子线程并不会退出
 * @author: 杨海波
 * @date: 2022-01-17
 **/
public class MainExit {

    public static void main(String[] args) {
        System.out.println("main enter");

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("executing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.setName("t1");
        thread.start();

        System.out.println("main exit");
        System.out.println("main exit");
        System.out.println("main exit");
        System.out.println("main exit");
        System.out.println("main exit");
    }
}
