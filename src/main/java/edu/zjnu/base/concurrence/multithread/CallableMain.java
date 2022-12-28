package edu.zjnu.base.concurrence.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: 杨海波
 * @date: 2022-12-23 15:33:26
 * @description: todo
 */
public class CallableMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new MyCallable();
        FutureTask<String> future = new FutureTask<String>(callable);
        // 子线程，异步计算开始
        new Thread(future).start();
        System.out.println("开始获取异步线程执行结果");
        // 主线程获取运算结果是同步过程，即 call 方法执行完成，才能获取结果
        String rz = future.get();
        System.out.println("已经得到异步线程执行结果");
        System.out.println(rz);
    }


    private static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            StringBuilder testStr = new StringBuilder("testStr:");
            for (int i = 0; i < 9; i++) {
                testStr.append(i);
                System.out.println("此时的循环是：" + 1);
                if (i % 3 == 0) {
                    Thread.sleep(100);
                }
            }
            return testStr.toString();
        }
    }
}
