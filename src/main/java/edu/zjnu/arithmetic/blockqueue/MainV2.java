package edu.zjnu.arithmetic.blockqueue;

/**
 * @description: ArrayBlockingQueueV2  同步的线程安全的，阻塞队列
 * @author: 杨海波
 * @date: 2022-01-12
 **/
public class MainV2 {

    public static void main(String[] args) {
        ArrayBlockingQueueV2<String> queue = new ArrayBlockingQueueV2<>();

        Thread producer = new Thread(() -> {
            try {
                queue.put("this");
                queue.put("is");
                queue.put("a");
                queue.put("test");
                queue.put("string");
                queue.put("hahaha");
                queue.put("xixixix");
                queue.put("list");
                queue.put("red");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread customer = new Thread(() -> {
            try {
                System.out.println(queue.get());
                System.out.println(queue.get());
                System.out.println(queue.get());
                System.out.println(queue.get());
                System.out.println(queue.get());
                System.out.println(queue.get());
                System.out.println(queue.get());
                System.out.println(queue.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        customer.start();

    }
}
