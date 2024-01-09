package com.gqz.thread;

/**
 * 创建线程方式二
 * 1、创建：实现Runnable接口 + 重写run
 * 2、启动：创建实现类对象,创建Thread类对象调用start方法
 *
 * @author ganquanzhong
 * @ClassName: StartThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:41:47
 */
public class StartRun implements Runnable {

    public static void main(String[] args) {
//		//创建实现类对象
//		StartRun sr = new StartRun();
//		//创建代理对象，传入参数
//		Thread t = new Thread(sr);
//		//启动，加入到线程调度器中，启动线程
//		t.start();
        //使用匿名类  ,某个对象只使用一次
        new Thread(new StartRun()).start();
        ;


        //调用run方法 ，普通调用 直接调用run（）方法，不是加到线程调度器，而是普通方法
        for (int i = 0; i < 20; i++) {
            System.out.println("一边coding");
        }
    }

    /**
     * 线程入口点
     */
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("一边听歌");
        }
    }
}
