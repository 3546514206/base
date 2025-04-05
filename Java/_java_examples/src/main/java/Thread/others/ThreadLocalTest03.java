package com.gqz.others;

/*
 * ThreadLocal: 分析上下文 环境 起点
 * 1、构造器：哪里调用就属于哪里的  找线程体
 * 2、run方法：本线程自身的
 */
public class ThreadLocalTest03 {
    //private static ThreadLocal<Integer>	threadLocal = new ThreadLocal<Integer>();
    //初始化  重写initialValue
//	private static ThreadLocal<Integer>	threadLocal = new ThreadLocal<Integer>() {
//		@Override
//		protected Integer initialValue() {
//			// TODO Auto-generated method stub
//			return 200;
//		}
//	};
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread()).start();
        }

    }

    //内部类
    public static class MyThread implements Runnable {
        //添加构造器
        public MyThread() {
            //设置值
            threadLocal.set(666);
            System.out.println(Thread.currentThread().getName() + "构造器里面的---->" + threadLocal.get());
        }

        public void run() {
            threadLocal.set((int) (Math.random() * 99));
            System.out.println(Thread.currentThread().getName() + "run()本线程里面的--->" + threadLocal.get());
        }
    }
}
