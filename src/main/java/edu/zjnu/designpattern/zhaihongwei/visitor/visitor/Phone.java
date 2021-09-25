package edu.zjnu.designpattern.visitor.visitor;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class Phone implements DigitalProduct {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
