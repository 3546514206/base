package edu.zjnu.arithmetic.blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 带条件变量的阻塞队列
 * @author: 杨海波
 * @date: 2022-01-13
 **/
public class ArrayBlockingQueueV3<E> implements BlockingQueue<E> {

    /**
     * 默认大小 16
     */
    private static final int DEFAULT_CAPACITY = 4;

    /**
     * 存储元素的数据结构
     */
    private final Object[] elements;
    /**
     * 可冲入锁
     */
    private final ReentrantLock reentrantLock;
    private final Condition condition;
    private int head, tail, count;


    public ArrayBlockingQueueV3() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBlockingQueueV3(int initCapacity) {
        assert initCapacity > 0;
        this.elements = new Object[initCapacity];
        this.head = 0;
        this.tail = 0;
        this.count = 0;

        this.reentrantLock = new ReentrantLock();
        this.condition = reentrantLock.newCondition();
    }

    @Override
    public void put(E e) throws InterruptedException {
        // 先尝试获得互斥锁，以进入临界区
        reentrantLock.lockInterruptibly();

        try {
            while (this.count == this.elements.length) {
                condition.await();
            }

            // 走到这里，说明当前队列不满，可以执行入队操作
            enqueue(e);

            // 唤醒可能等待着的消费者线程
            // 由于共用了一个condition，所以不能用signal，否则一旦唤醒的也是生产者线程就会陷入上面的while死循环）
            condition.signalAll();
        } finally {
            //元素e入队完毕，释放锁
            reentrantLock.unlock();
        }


    }

    @Override
    public E get() throws InterruptedException {
        // 先尝试获得互斥锁，以进入临界区
        reentrantLock.lockInterruptibly();

        try {
            // 因为被生产者唤醒后可能会被其它的消费者消费而使得队列再次为空，需要循环的判断
            while (this.count == 0) {
                condition.await();
            }

            E e = dequeue();

            // 唤醒可能等待着的生产者线程
            // 由于共用了一个condition，所以不能用signal，否则一旦唤醒的也是消费者线程就会陷入上面的while死循环）
            condition.signalAll();

            return e;
        } finally {
            // 出队完毕，释放锁
            reentrantLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    private E dequeue() {
        E e = (E) this.elements[head];
        this.head = getIndex(head + 1);
        this.elements[head] = null;
        this.count--;
        return e;
    }

    private void enqueue(E e) {
        this.elements[tail] = e;
        this.tail = getIndex(tail + 1);
        this.count++;
    }

    private int getIndex(int logicIndex) {
        int innerLength = this.elements.length;
        if (logicIndex < 0) {
            //其实这里永远也不会走进来
            return logicIndex + innerLength;
        } else if (logicIndex >= innerLength) {
            return logicIndex - innerLength;
        } else {
            return logicIndex;
        }
    }
}
