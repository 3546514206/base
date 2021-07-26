package edu.zjnu.designpattern.visitor.visit;

/**
 * Create by zhaihongwei on 2018/4/3
 * 具体节点类
 */
public class Man implements Human {

    @Override
    public void accept(Visitor visitor) {
        visitor.getManState(this);
    }
}
