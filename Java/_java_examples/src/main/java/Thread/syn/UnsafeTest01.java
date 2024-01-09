package com.gqz.syn;


/**
 * 12306 抢票
 * 线程不安全
 *
 * @author ganquanzhong
 * @ClassName: Web12306
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:51:54
 */
public class UnsafeTest01 implements Runnable {
    private boolean flag = true;
    private int ticketNums = 10;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        //一份资源
        UnsafeTest01 web = new UnsafeTest01();


        //多个代理
        new Thread(web, "黄牛1").start();
        new Thread(web, "mike").start();
        new Thread(web, "小明").start();

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (flag) {
            test();
        }
    }

    public void test() {
        if (ticketNums < 0) {
            flag = false;
            return;
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
