package com.reactive;

import java.util.concurrent.TimeUnit;

/**
 * 假设有一个数据加载器，分别加载配置、用户信息以及订单信息
 * 作者：小马哥mercyblitz
 * 链接：https://www.imooc.com/article/46306
 * 来源：慕课网
 * 本文原创发布于慕课网 ，转载请注明出处，谢谢合作
 * @create 4:14 PM 10/09/2018
 */
public class DataLoader {

    public final void load() {
        long startTime = System.currentTimeMillis(); //开始时间
        doLoad(); // 具体执行
        long costTime = System.currentTimeMillis() - startTime; // 消耗时间
        System.out.println("load() 总耗时：" + costTime + " 毫秒");
    }

    protected void doLoad() {
        // 串行计算
        loadConfigurations(); // 耗时 1s
        loadUsers(); // 耗时 2s
        loadOrders(); // 耗时 3s
    } // 总耗时 1s + 2s + 3s = 6s

    protected final void loadConfigurations() {
        loadMock("loadConfigurations()", 1);
    }

    protected final void loadUsers() {
        loadMock("loadUsers()", 2);
    }

    protected final void loadOrders() {
        loadMock("loadOrders()", 3);
    }

    private void loadMock(String source, int seconds) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(milliseconds);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程 : %s] %s 耗时 : %d 毫秒\n", Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new DataLoader().load();
    }

}
