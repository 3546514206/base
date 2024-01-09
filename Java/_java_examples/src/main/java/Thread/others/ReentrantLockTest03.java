package com.gqz.others;

/**
 * @author ganquanzhong
 * @ClassName: ReentrantLockTest02
 * @Description: 模拟可重入锁：同一个线程被锁定能继续进行，可延续
 * @date 2019年7月11日 下午5:03:46
 */
public class ReentrantLockTest03 {
    ReLock reLock = new ReLock();

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockTest03().a();
    }

    public void a() throws InterruptedException {
        reLock.locked();
        System.out.println("第一次进入锁");
        doSomething();//在可重入锁时，同一个线程仍能延续
        reLock.unLocked();
    }

    public void doSomething() throws InterruptedException {
        reLock.locked();
        System.out.println("第二次进入锁     此时为可重入");
        reLock.unLocked();
    }
}

//不可重入锁
class ReLock {
    //是否占用
    private boolean isLocked = false;
    //记录线程  用于判断是否为同一个线程
    private Thread threadBy = null;
    private int holdCount;

    public int getHoldCount() {
        return holdCount;
    }

    //使用锁
    public synchronized void locked() throws InterruptedException {
        Thread thread = Thread.currentThread();
        //此时为可重入锁，当不同线程且锁住时等待
        if (isLocked && thread != threadBy) {
            wait();
        }

        isLocked = true;
        threadBy = thread; //记录当前线程
        holdCount++; //计数器加一

        System.out.println("锁数为：" + getHoldCount());
    }

    //释放锁
    public synchronized void unLocked() {
        //释放锁时，只有同一个线程才会释放
        if (Thread.currentThread() == threadBy) {
            holdCount--;
            if (holdCount == 0) {
                isLocked = false;
                notify();
                threadBy = null;
            }
        }

        System.out.println("锁数为：" + getHoldCount());
    }
}


