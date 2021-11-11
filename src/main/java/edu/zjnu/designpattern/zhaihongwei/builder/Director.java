package edu.zjnu.designpattern.zhaihongwei.builder;

/**
 * Create by zhaihongwei on 2018/3/13
 */
public class Director {

    private AbstractBuilder builder;

    public void setBuilder(AbstractBuilder builder) {
        this.builder = builder;
    }

    public Product getProduct() {
        builder.buildName();
        builder.buildPrice();
        return builder.getProduct();
    }
}
