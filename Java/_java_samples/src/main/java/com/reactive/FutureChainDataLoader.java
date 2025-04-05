package com.reactive;

import java.util.concurrent.CompletableFuture;

/**
 * 作者：小马哥mercyblitz
 * 链接：https://www.imooc.com/article/46306
 * 来源：慕课网
 * 本文原创发布于慕课网 ，转载请注明出处，谢谢合作
 *
 * @create 4:28 PM 10/09/2018
 */
public class FutureChainDataLoader extends DataLoader {
    protected void doLoad() {
        CompletableFuture
                .runAsync(super::loadConfigurations)
                .thenRun(super::loadUsers)
                .thenRun(super::loadOrders)
                .whenComplete((result, throwable) -> { // 完成时回调
            System.out.println("加载完成");
        }).join(); // 等待完成
    }

    public static void main(String[] args) {
        new FutureChainDataLoader().load();
    }


}
