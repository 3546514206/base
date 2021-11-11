package edu.zjnu.designpattern.zhaihongwei.factorymethod.src.factorymethod.common;

/**
 * Create by zhaihongwei on 2018/3/9
 */
public class FactoryTest {

    public static void main(String[] args) {
        PeopleFactory factory = new PeopleFactory();
        People chinese = factory.everyOneSay("Chinese");
        chinese.say();
    }
}
