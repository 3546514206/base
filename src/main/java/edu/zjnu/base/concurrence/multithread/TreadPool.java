package edu.zjnu.base.concurrence.multithread;

import edu.zjnu.base.LogInterFace;

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
            executorService.execute(() -> {
                log.info("线程名称：" + Thread.currentThread().getName());
            });
        }


        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    }
}
