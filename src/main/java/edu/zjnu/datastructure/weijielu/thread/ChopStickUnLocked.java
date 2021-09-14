package edu.zjnu.datastructure.weijielu.thread;

/**
 * @author weijielu
 */
public class ChopStickUnLocked extends ChopStickLocked {
    public ChopStickUnLocked() {
        super();
    }

    public boolean pickUp() {
        return lock.tryLock();
    }

}
