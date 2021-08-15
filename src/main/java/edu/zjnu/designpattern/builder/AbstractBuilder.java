package edu.zjnu.designpattern.builder;

/**
 * Create by zhaihongwei on 2018/3/13
 */
public abstract class AbstractBuilder {

    public abstract void buildName();

    public abstract void buildPrice();

    public abstract Product getProduct();
}
