package edu.zjnu.base.concurrence.multithread;

/**
 * @description: InterruptMain
 * ①：调用Thread.currentThread().interrupt()方法并不会立刻中断当前线程，只有等当前线程阻塞在类似sleep和wait等操作上才会执行
 * ②：interrupt()会发出中断命令，而isInterrupted（）和interrupted（）并不会发出中断线程的命令；
 * isInterrupted（）和interrupted（）的区别在于 interrupted会清除中断的状态，所以上面实例程序会一直运行。如果注释掉第三点(catch代
 * 码库第三条)，则程序会在下一次到达sleep的时候终止。
 * @author: 杨海波
 * @date: 2022-07-25 16:18
 **/
public class InterruptMain {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    // i happy run , please break me
                    System.out.println("I'm runing " + InterruptMain.i++);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("exception。。。");
                        //①:发出中断请求，设置中断状态
                        Thread.currentThread().interrupt();
                        //②:判断中断状态（不清除中断状态）
                        System.out.println(Thread.currentThread().isInterrupted());
                        //③:判断中断状态（同时清除中断状态）
                        System.out.println(Thread.interrupted());
                    }
                    System.out.println("current thread haven't been broken");
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
