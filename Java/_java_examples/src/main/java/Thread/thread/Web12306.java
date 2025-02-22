package com.gqz.thread;


/**
 * 12306 抢票
 *
 * @author ganquanzhong
 * @ClassName: Web12306
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:51:54
 */
public class Web12306 implements Runnable {
    private int ticketNums = 99;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        //一份资源
        Web12306 web = new Web12306();
        //多个代理
        new Thread(web, "黄牛1").start();
        new Thread(web, "mike").start();
        new Thread(web, "小明").start();

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                //线程休眠
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }
}
