package com.gqz.others;

/*
 * ThreadLocal: 每个线程自身的数据，更改不会影响其他线程的
 */
public class ThreadLocalTest02 {
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
        public void run() {
            Integer left = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + "得到了---->" + left);
            //设置值
            threadLocal.set(left - 1);
            System.out.println(Thread.currentThread().getName() + "还剩下---->" + threadLocal.get());
        }
    }
}
