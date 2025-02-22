package com.gqz.state;

/**
 * @author ganquanzhong
 * @ClassName: YieldDemo01
 * @Description: yield
 * @date 2019年7月8日 下午5:01:13
 */
public class YieldDemo01 {
    public static void main(String[] args) {
        MyYield my = new MyYield();
        new Thread(my, "a").start();
        new Thread(my, "b").start();
    }
}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->start");
        //礼让线程、暂停线程   直接进入就绪状态
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "--->end");
    }
}