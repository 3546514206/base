package edu.zjnu.designpattern.zhaihongwei.visitor.visitor;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class VisitorTest {

    public static void main(String[] args) {
        DigitalProduct phone = new Phone();
        Visitor visitor = new IphoneVisitor();
        phone.accept(visitor);
    }
}
