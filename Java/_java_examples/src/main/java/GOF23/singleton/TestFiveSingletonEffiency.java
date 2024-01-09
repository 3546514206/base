package com.gqz.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 测试多线程环境下五种创建单例模式的效率
 *
 * @author ganquanzhong
 * @ClassName: TestFiveSingletonEffiency
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月19日 下午3:19:14
 */
public class TestFiveSingletonEffiency {
    public static void testHungryType() {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            //匿名内部类
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        //饿汉式单例
                        Object o = SingletonByHungryType.getInstance();
                    }
                    //线程数减一
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await(); //main线程阻塞，直到计数器变为0，才会继续往下执行！
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("饿汉式单例总耗时：" + (end - start));
    }


    public static void testLazyLoad() {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            //匿名内部类
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        //懒汉式单例模式
                        Object o = SingletonLazyLoad.getInstance();
                    }
                    //线程数减一
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await(); //main线程阻塞，直到计数器变为0，才会继续往下执行！
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("饿汉式单例总耗时：" + (end - start));
    }


    public static void testDCL() {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            //匿名内部类
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        Object o = SingletonByDCL.getInstance();
                    }
                    //线程数减一
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await(); //main线程阻塞，直到计数器变为0，才会继续往下执行！
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("双重检测单例总耗时：" + (end - start));
    }

    public static void testStaticInnerClass() {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            //匿名内部类
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        //静态内部类
                        Object o = SingletonStaticInnerClass.getInstance();
                    }
                    //线程数减一
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await(); //main线程阻塞，直到计数器变为0，才会继续往下执行！
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("静态内部类单例总耗时：" + (end - start));
    }

    public static void testEnum() {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            //匿名内部类
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000000; i++) {
                        Object o = SingletonByEnum.INSTANCE;
                    }
                    //线程数减一
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await(); //main线程阻塞，直到计数器变为0，才会继续往下执行！
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("枚举单例总耗时：" + (end - start));
    }


    public static void main(String[] args) throws Exception {
        testHungryType();
        testLazyLoad();
        testDCL();
        testStaticInnerClass();
        testEnum();

    }
}
