package edu.zjnu.designpattern.zhaihongwei.builder;

/**
 * Create by zhaihongwei on 2018/3/13
 */
public class XiaoMiBuilder extends AbstractBuilder {

    private Product product;

    public XiaoMiBuilder() {
        product = new Product();
    }

    @Override
    public void buildName() {
        product.setName("小米手机");
    }

    @Override
    public void buildPrice() {
        product.setPrice(2999);
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
