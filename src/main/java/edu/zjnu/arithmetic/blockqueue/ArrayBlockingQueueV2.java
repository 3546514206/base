package edu.zjnu.arithmetic.blockqueue;

/**
 * @description: ArrayBlockingQueueV2
 * @author: 杨海波
 * @date: 2022-01-12
 **/
public class ArrayBlockingQueueV2<E> implements BlockingQueue<E> {

    /**
     * 默认大小 16
     */
    private static final int DEFAULT_CAPACITY = 4;

    /**
     * 存储元素的数据结构
     */
    private final Object[] elements;
    private int head, tail, count;

    public ArrayBlockingQueueV2() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBlockingQueueV2(int initCapacity) {
        assert initCapacity > 0;
        this.elements = new Object[initCapacity];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    @Override
    public void put(E e) throws InterruptedException {
        while (true) {
            synchronized (this) {
                // ???
                if (count != this.elements.length) {
                    enqueue(e);
                    return;
                }
            }
            Thread.sleep(1000L);
        }
    }

    @Override
    public E get() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (count > 0) {
                    return dequeue();
                }
            }
            Thread.sleep(1000L);
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
