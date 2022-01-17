package edu.zjnu.base.multithread;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * @description:
 * @author: 杨海波
 * @date: 2022-01-17
 **/
public class MainExitV2 {

    public static void main(String[] args) {
        System.out.println("main enter");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    int a = 1;
                    System.out.println("executing" + a);

                }
            }
        });

        thread.setName("t2");
        // 如果设置为守护线程，那么守护线程会随着主线程退出而退出
        thread.setDaemon(true);
        thread.start();

        System.out.println("main exit");
        System.out.println("main exit");
        System.out.println("main exit");
        System.out.println("main exit");
        System.out.println("main exit");
    }
}
