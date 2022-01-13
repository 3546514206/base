package edu.zjnu.arithmetic.blockqueue;

/**
 * @description: 基于数组的实现
 * @author: 杨海波
 * @date: 2022-01-12
 **/
public class ArrayBlockingQueueV1<E> implements BlockingQueue<E> {

    /**
     * 默认的大小：16
     */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    /**
     * 承载队列元素的数组
     */
    private final Object[] elements;

    /**
     * 队列头部
     */
    private int head;

    /**
     * 队列尾部(表示下一个添加的位置，也就是说tail位置上一般情况下都为null)
     */
    private int tail;


    private int count;

    public ArrayBlockingQueueV1() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBlockingQueueV1(int initCapacity) {
        assert initCapacity > 0;
        this.elements = new Object[initCapacity];
        // 初始化队列 头部,尾部下标
        this.head = 0;
        this.tail = 0;
        //初始状态元素个数为0
        this.count = 0;
    }

    @Override
    public void put(E e) throws InterruptedException {
        enqueue(e);
    }

    /**
     * 入队列
     * @param e
     */
    private void enqueue(E e) {
        // 存放新插入的元素
        this.elements[this.tail] = e;
        // 尾部插入新元素后 tail下标后移一位
        this.tail = getIndex(this.tail + 1);

        this.count++;
    }

    /**
     *
     *
     * @param logicIndex
     * @return
     */
    private int getIndex(int logicIndex) {
        int innerArrayLength = this.elements.length;

        // 由于队列下标逻辑上是循环的
        if (logicIndex < 0) {
            // 当逻辑下标小于零时
            // 真实下标 = 逻辑下标 + 加上当前数组长度
            return logicIndex + innerArrayLength;
        } else if (logicIndex >= innerArrayLength) {
            // 当逻辑下标大于数组长度时
            // 真实下标 = 逻辑下标 - 减去当前数组长度
            return logicIndex - innerArrayLength;
        } else {

            // 真实下标 = 逻辑下标
            return logicIndex;
        }
    }

    @Override
    public E get() throws InterruptedException {
        E ele = (E) this.elements[head];
        this.elements[head] = null;
        head = getIndex(head + 1);
        this.count--;

        return ele;
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}
