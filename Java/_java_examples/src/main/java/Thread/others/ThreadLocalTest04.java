package com.gqz.others;

/*
 * InheritableThreadLocal: 继承上下文 环境的数据  拷贝一份给子线程
 */
public class ThreadLocalTest04 {
    //private static ThreadLocal<Integer>	threadLocal = new ThreadLocal<Integer>();
    //初始化  重写initialValue
//	private static ThreadLocal<Integer>	threadLocal = new ThreadLocal<Integer>() {
//		@Override
//		protected Integer initialValue() {
//			// TODO Auto-generated method stub
//			return 200;
//		}
//	};
    //InheritableThreadLocal 可以继承上下文
    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>();

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
            System.out.println(Thread.currentThread().getName() + "run()本线程里面的--->" + threadLocal.get());
        }
    }
}
