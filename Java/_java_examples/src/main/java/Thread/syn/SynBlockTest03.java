package com.gqz.syn;

/**
 * 12306 抢票 使用synchronized
 *
 * @author ganquanzhong
 * @ClassName: Web12306
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:51:54
 */
public class SynBlockTest03 implements Runnable {
    private boolean flag = true;
    private int ticketNums = 10;

    public static void main(String[] args) {
//		main 线程
//		System.out.println(Thread.currentThread().getName());
        // 一份资源
        SynBlockTest03 web = new SynBlockTest03();
        // 多个代理
        new Thread(web, "黄牛").start();
        new Thread(web, "陈蓉").start();
        new Thread(web, "小明").start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (flag) {
            test2();
        }
    }

    //线程安全：尽可能锁定合理的范围 （不是只代码，指数据的完整性）
    //double checking
    // 加上对象锁，将共享的资源排他锁
    public void test2() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        synchronized (this) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }
            try {
                // 线程休眠
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    // 加上对象锁，将共享的资源排他锁
    public void test1() {
        synchronized (this) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }
            try {
                // 线程休眠
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    // 加上对象锁，将共享的资源排他锁
    public synchronized void test() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            // 线程休眠
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
    }

}
