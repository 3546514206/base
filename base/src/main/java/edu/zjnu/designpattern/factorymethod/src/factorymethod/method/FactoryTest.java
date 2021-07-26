package edu.zjnu.designpattern.factorymethod.src.factorymethod.method;


/**
 * Create by zhaihongwei on 2018/3/9
 */
public class FactoryTest {

    public static void main(String[] args) {
        PeopleFactory factory = new PeopleFactory();
        People chinese = factory.createChinese();
        chinese.say();
    }
}
