package com.gqz.others;

/*
 * ThreadLocal: 每个线程自身的存储本地、局部区域
 */
public class ThreadLocalTest01 {
    //private static ThreadLocal<Integer>	threadLocal = new ThreadLocal<Integer>();
    //初始化  重写initialValue
//	private static ThreadLocal<Integer>	threadLocal = new ThreadLocal<Integer>() {
//		@Override
//		protected Integer initialValue() {
//			// TODO Auto-generated method stub
//			return 200;
//		}
//	};
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 300);

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "---->" + threadLocal.get());
        //设置值
        threadLocal.set(99);
        System.out.println(Thread.currentThread().getName() + "---->" + threadLocal.get());
        new Thread(new MyThread()).start();

    }

    //内部类
    public static class MyThread implements Runnable {
        public void run() {
            threadLocal.set((int) (Math.random() * 99));
            System.out.println(Thread.currentThread().getName() + "---->" + threadLocal.get());

        }
    }
}
