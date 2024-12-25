package com.reactive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 作者：小马哥mercyblitz
 * 链接：https://www.imooc.com/article/46306
 * 来源：慕课网
 * 本文原创发布于慕课网 ，转载请注明出处，谢谢合作
 * @create 4:25 PM 10/09/2018
 */
public class FutureBlockingDataLoader extends DataLoader {

    protected void doLoad() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 创建线程池
        runCompletely(executorService.submit(super::loadConfigurations)); // 耗时 >= 1s
        runCompletely(executorService.submit(super::loadUsers)); // 耗时 >= 2s
        runCompletely(executorService.submit(super::loadOrders)); // 耗时 >= 3s
        executorService.shutdown();
    } // 总耗时 sum(>= 1s, >= 2s, >= 3s) >= 6s

    private void runCompletely(Future<?> future) {
        try {
            future.get(); // 阻塞等待结果执行
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new FutureBlockingDataLoader().load();
    }

}
