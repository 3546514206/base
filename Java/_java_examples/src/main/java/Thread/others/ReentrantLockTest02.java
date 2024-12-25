package com.gqz.others;
/*
 * 不可重入锁：同一个线程被锁定了不能继续进行
 */

/**
 * @author ganquanzhong
 * @ClassName: ReentrantLockTest02
 * @Description: 模拟不可重入锁：同一个线程被锁定了不能继续进行
 * @date 2019年7月11日 下午5:03:46
 */
public class ReentrantLockTest02 {
    Lock lock = new Lock();

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockTest02().a();
    }

    public void a() throws InterruptedException {
        lock.locked();
        System.out.println("第一次进入锁");
        doSomething();//在不可重入锁时，即使同一个线程还是无法进入
        lock.unLocked();
    }

    public void doSomething() throws InterruptedException {
        lock.locked();
        System.out.println("第二次进入锁可重入");
        lock.unLocked();
    }
}

//不可重入锁
class Lock {
    //是否占用
    private boolean isLocked = false;

    //使用锁
    public synchronized void locked() throws InterruptedException {
        //如果锁上了，则wait
        if (isLocked) {
            wait();
        }
        isLocked = true;
    }

    //释放锁
    public synchronized void unLocked() {
        isLocked = false;
        notify();
    }
}


