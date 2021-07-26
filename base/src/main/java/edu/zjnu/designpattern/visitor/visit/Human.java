package edu.zjnu.designpattern.visitor.visit;


/**
 * Create by zhaihongwei on 2018/4/3
 */
public interface Human {

    /**
     * 接受状态对象
     *
     * @param visitor
     */
    void accept(Visitor visitor);
}