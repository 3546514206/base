package edu.zjnu.base.multithread;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-11-28
 **/
public class NoVisibility {

    private static boolean ready;

    private static int number;

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

    private static class ReaderThread extends Thread {

        public void run() {

            while (!ready) {
                /**
                 * 而yield()：
                 * 暂停当前正在执行的线程对象，并执行其他线程。在多线程的情况下，由CPU决定执行
                 * 哪一个线程，而yield()方法就是暂停当前的线程，让给其他线程（包括它自己）执行，
                 * 具体由谁执行由CPU决定。注意：被执行的方法可以是其他线程，也可以是原来的那个线程。
                 */
                Thread.yield();
            }
            System.out.println(number);
        }
    }
}

