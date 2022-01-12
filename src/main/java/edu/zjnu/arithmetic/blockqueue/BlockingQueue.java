package edu.zjnu.arithmetic.blockqueue;

/**
 * @description: 阻塞队列
 * @author: 杨海波
 * @date: 2022-01-12
 **/
public interface BlockingQueue<E> {

    void put(E e) throws InterruptedException;


    E get() throws InterruptedException;

    boolean isEmpty();
}
