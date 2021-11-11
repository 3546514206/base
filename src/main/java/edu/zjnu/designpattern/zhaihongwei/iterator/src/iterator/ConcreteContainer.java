package edu.zjnu.designpattern.zhaihongwei.iterator.src.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/3/27
 * 具体容器类，用来存放聚合对象中遍历的每一个元素
 */
public class ConcreteContainer<E> implements MyContainer {

    public List<E> list = new ArrayList<>();

    /**
     * 在容器中添加指定元素
     *
     * @param e
     */
    @Override
    public void add(Object e) {
        list.add((E) e);
    }

    /**
     * 删除容器中的指定元素
     */
    @Override
    public void remove(Object e) {
        list.remove(e);
    }

    /**
     * 获取具体的迭代器对象
     *
     * @return
     */
    @Override
    public MyIterator getMyIterator(MyIterator myIterator) {
        return myIterator;
    }
}
