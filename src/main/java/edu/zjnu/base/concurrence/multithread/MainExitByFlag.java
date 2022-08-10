package edu.zjnu.base.concurrence.multithread;

/**
 * @description: 通过标志位退出来退出线程
 * @author: 杨海波
 * @date: 2022-01-17
 **/
public class MainExitByFlag {

    public static void main(String[] args) throws InterruptedException {
        T1Thread t1 = new T1Thread();
        t1.start();

        Thread.sleep(10000);
        t1.stopT1Thread();
    }
}

class T1Thread extends Thread {

    private boolean stopped = false;

    private long a = 1L;

    @Override
    public void run() {

        while (!stopped) {
            //stopped无法被更新，线程无法退出
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            a++;
            System.out.println(a);
        }
    }

    public void stopT1Thread() {
        this.stopped = true;
    }
}
