package edu.zjnu.designpattern.zhaihongwei.visitor.visit;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public interface Visitor {

    /**
     * 获得男人的状态
     *
     * @param man
     */
    void getManState(Man man);

    /**
     * 获得女人的状态
     *
     * @param woman
     */
    void getWomanState(Woman woman);
}
