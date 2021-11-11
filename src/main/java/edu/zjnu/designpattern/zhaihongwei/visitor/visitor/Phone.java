package edu.zjnu.designpattern.zhaihongwei.visitor.visitor;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class Phone implements DigitalProduct {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
