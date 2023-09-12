package edu.zjnu.base.concurrence.multithread;

import edu.zjnu.base.base.LogInterFace;

import java.util.concurrent.*;

/**
 * @author 杨海波
 * @description 线程池demo
 * @date 2021-05-10
 */
public class TreadPool implements LogInterFace {
    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 999; i++) {
            log.info("当前循环：" + i);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名称：" + Thread.currentThread().getName());
                }
            });
        }

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return newThread(r);
            }
        };

        // 一下几种线程池本质上都是 ThreadPoolExecutor
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService fixedThreadPool2 = Executors.newFixedThreadPool(10, threadFactory);
        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        ExecutorService cachedExecutor2 = Executors.newCachedThreadPool(threadFactory);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService singleThreadExecutor2 = Executors.newSingleThreadExecutor(threadFactory);

        MyThread thread = new MyThread();
        fixedThreadPool.execute(thread);
    }


    private static class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 已经执行。");
        }
    }

}
