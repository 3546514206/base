package edu.zjnu.designpattern.iterator.src.iterator;

/**
 * Create by zhaihongwei on 2018/3/27
 * 抽象迭代器类
 */
public interface MyIterator<E> {

    boolean hasNext();

    E next();
}
