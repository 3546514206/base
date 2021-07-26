package edu.zjnu.designpattern.decorator;

/**
 * Create by zhaihongwei on 2018/3/16
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Component source = new Source();
        Component decorator = new Decorator(source);
        decorator.method();
    }
}
