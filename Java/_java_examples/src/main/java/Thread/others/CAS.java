package com.gqz.others;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author ganquanzhong
 * @ClassName: CAS
 * @Description: CAS: compare and swap  比较并交换
 * @date 2019年7月11日 下午5:32:00
 */
public class CAS {
    private static AtomicInteger stock = new AtomicInteger(6);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new SimpleDateFormat("YYYY-MM-dd HH:MM:ss.SSS").format(new Date()));
            //模拟网络延时
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            new Thread(() -> {
                Integer left = stock.decrementAndGet();
                if (left < 0) {
                    System.out.println("商品已经抢完了！！！");
                    return;
                }
                System.out.print(Thread.currentThread().getName() + "成功抢购了一件商品！");
                System.out.println("还剩下 " + left + " 件商品");
            }).start();
        }
    }
}
