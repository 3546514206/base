package edu.zjnu.base.concurrence;

import java.util.concurrent.*;

/**
 * @author: 杨海波
 * @date: 2022-11-02 09:36:37
 * @description: FutureTaskMain
 */
public class FutureTaskMain {

    public static void main(String[] args) {
        case_01();
        case_02();
    }

    /**
     * 异步任务交给一个线程类去执行
     */
    private static void case_02() {
        //构造传入一个Callable，这里直接传入Lambda表达式，注意只有一行时return是可以省略的；
        FutureTask<Integer> task = new FutureTask<>(() -> 1 + 8);
        Thread thread = new Thread(task);
        thread.start();
        int result = 0;
        try {
            result = task.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("异步线程执行失败");
        }

        System.out.println(result);
    }

    /**
     * 异步任务提交给线程池去执行
     */
    private static void case_01() {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1 + 2;
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(task);

        int result = 0;
        try {
            result = task.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("异步线程执行失败");
        }

        System.out.println(result);

    }

}
