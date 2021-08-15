package edu.zjnu.designpattern.iterator.src.iterator;

/**
 * Create by zhaihongwei on 2018/3/27
 * 抽象容器类
 */
public interface MyContainer<E> {
    void add(E e);

    void remove(E e);

    MyIterator getMyIterator(MyIterator myIterator);
}
