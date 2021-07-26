package edu.zjnu.base.multithread;

/**
 * @author 杨海波
 * @description Object中定义的关于线程先关的方法:wait() notify() notifyAll()
 * @date 2021-03-14 16:34
 */
public class ObjectMethod {

    public static void main(String[] args) throws InterruptedException {

        // wait方法
        objectWait();
    }


    /**
     * 在调用wait方法时，当前线程必须持有被调用对象的锁，否则会抛出IllegalMonitorStateException异常
     *
     * @throws InterruptedException
     */
    private static void objectWait() throws InterruptedException {
        Object o = new Object();

//        o.wait();

        // 当前线程获取对象o的锁
        synchronized (o) {

            // 不会释放任何锁资源
            Thread.sleep(100);

            // 当前线程失去对象o的锁，进入等待状态
            o.wait();
        }
    }
}
