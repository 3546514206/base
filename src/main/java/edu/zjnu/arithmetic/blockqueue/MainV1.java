package edu.zjnu.arithmetic.blockqueue;

/**
 * @description: MainV1
 * @author: 杨海波
 * @date: 2022-01-12
 **/
public class MainV1 {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueueV1<String> queue = new ArrayBlockingQueueV1<>();
        queue.put("message");
        queue.put("this");
        queue.put("test");

        assert !queue.isEmpty();
        System.out.println(queue.get());
        System.out.println(queue.get());
        queue.put("old");
        System.out.println(queue.get());
    }
}
