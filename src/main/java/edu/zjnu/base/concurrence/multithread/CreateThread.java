package edu.zjnu.base.concurrence.multithread;

/**
 * @author 杨海波
 * @description 如何创建一个线程
 * @date 2021-03-14 13:26
 */
public class CreateThread {

    public static void main(String[] args) {
        //方法一
        SubThread subThread = new SubThread();
        subThread.start();

        //方法二
        Thread runnableImpl = new Thread(new RunnableImpl());
        runnableImpl.start();
    }
}

class SubThread extends Thread {

    @Override
    public void run() {
        System.out.println("继承Thread类，重写run方法，执行Thread类的start方法");
    }
}

class RunnableImpl implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(
                "实现Runnable接口，实现该接口的run方法，并" +
                        "将Runnable的自定义实现类作为参数传入" +
                        "到一个Thread类中去，执行Thread类的start方法"
        );
    }
}


