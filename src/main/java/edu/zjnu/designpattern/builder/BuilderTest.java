package edu.zjnu.designpattern.builder;

/**
 * Create by zhaihongwei on 2018/3/13
 */
public class BuilderTest {

    public static void main(String[] args) {
        Director director = new Director();

        AbstractBuilder builder = new AppleBuilder();
        director.setBuilder(builder);
        Product product = director.getProduct();
        System.out.println(product);


        AbstractBuilder builder2 = new XiaoMiBuilder();
        director.setBuilder(builder2);
        Product product2 = director.getProduct();
        System.out.println(product2);
    }
}
