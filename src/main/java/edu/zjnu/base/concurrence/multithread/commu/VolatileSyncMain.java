package edu.zjnu.base.concurrence.multithread.commu;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 通过 volatile 关键字声明的变量通信
 * @author: 杨海波
 * @date: 2022-07-04 17:12
 **/
public class VolatileSyncMain {

    private static volatile boolean notice = false;

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                list.add(1);
                if(list.size() == 68){
                    notice = true;
                    System.out.println("线程1将notice设置为true");
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(notice){
                        System.out.println("线程2执行自己的业务逻辑");
                        break;
                    }
                }
            }
        });

        thread2.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();


    }
}
