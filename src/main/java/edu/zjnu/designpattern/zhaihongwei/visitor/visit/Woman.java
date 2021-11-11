package edu.zjnu.designpattern.zhaihongwei.visitor.visit;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class Woman implements Human {

    @Override
    public void accept(Visitor visitor) {
        visitor.getWomanState(this);
    }
}
