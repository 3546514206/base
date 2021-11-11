package edu.zjnu.designpattern.zhaihongwei.iterator.src.iterator;


import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/3/27
 * 聚合对象类，需要实现抽象迭代器类
 */
public class AggregateObject<E> implements MyIterator {

    public List<E> list = new ArrayList<>();
    private int index;// 索引

    /**
     * 判断聚合对象中是否还有下一个元素
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        // 没有下一个元素了
        return list.size() != index;
    }

    /**
     * 返回聚合对象中的下一个元素
     *
     * @return
     */
    @Override
    public E next() {
        E e = null;
        if (this.hasNext()) {
            e = list.get(index++);
        }
        return e;
    }
}
