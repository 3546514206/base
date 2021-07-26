package edu.zjnu.designpattern.visitor.visitor;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public interface DigitalProduct {

    void accept(Visitor visitor);
}
