package com.reactive;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：小马哥mercyblitz
 * 链接：https://www.imooc.com/article/46306
 * 来源：慕课网
 * 本文原创发布于慕课网 ，转载请注明出处，谢谢合作
 * @create 4:21 PM 10/09/2018
 */
public class ParallelDataLoader extends DataLoader {

    protected void doLoad() {
        // 并行计算
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 创建线程池
        CompletionService completionService = new ExecutorCompletionService(executorService);
        completionService.submit(super::loadConfigurations, null); // 耗时 >= 1s
        completionService.submit(super::loadUsers, null); // 耗时 >= 2s
        completionService.submit(super::loadOrders, null); // 耗时 >= 3s
        int count = 0;
        while (count < 3) {
            // 等待三个任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
    } // 总耗时 max(1s, 2s, 3s) >= 3s

    public static void main(String[] args) {
        new ParallelDataLoader().load();
    }

}
