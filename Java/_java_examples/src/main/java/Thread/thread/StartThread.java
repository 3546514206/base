package com.gqz.thread;

/**
 * 创建线程方式一
 * 1、创建：继承Thread + 重写run
 * 2、启动：创建子类对象,调用start方法
 *
 * @author ganquanzhong
 * @ClassName: StartThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:41:47
 */
public class StartThread extends Thread {

    public static void main(String[] args) {
        //创建子类对象
        StartThread st = new StartThread();
        //启动，加入到线程调度器中，启动线程
        st.start();
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
