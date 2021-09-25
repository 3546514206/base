package edu.zjnu.designpattern.builder;

/**
 * Create by zhaihongwei on 2018/3/13
 */
public class AppleBuilder extends AbstractBuilder {

    private Product product;

    public AppleBuilder() {
        product = new Product();
    }

    @Override
    public void buildName() {

        product.setName("苹果手机");
    }

    @Override
    public void buildPrice() {
        product.setPrice(8999);
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
