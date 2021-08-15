package edu.zjnu.base.concurrence;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: callable
 * @author: 杨海波
 * @date: 2021-07-25
 **/
public class MyCallable implements Callable<Integer> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread thread = new Thread(ft);
        thread.start();
        System.out.println(ft.get());
    }

    @Override
    public Integer call() throws Exception {
        int a = 1;
        int b = 2;
        return a + b;
    }
}
