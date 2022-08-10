package edu.zjnu.base.concurrence.multithread;

import java.util.concurrent.Semaphore;

/**
 * @description: Semaphore
 * @author: 杨海波
 * @date: 2022-08-10 15:42
 **/
public class SemaphoreMain {

        public static void main(String[] args) {
            PrintQueue printQueue = new PrintQueue() ;

            PrintThread a = new PrintThread(printQueue) ;
            a.setName("A");

            PrintThread b = new PrintThread(printQueue);
            b.setName("B");

            PrintThread c = new PrintThread(printQueue) ;
            c.setName("C");

            a.start();
            b.start();
            c.start();
        }

}




