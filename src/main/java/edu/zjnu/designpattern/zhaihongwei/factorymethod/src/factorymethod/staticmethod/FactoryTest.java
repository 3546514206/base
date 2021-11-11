package edu.zjnu.designpattern.zhaihongwei.factorymethod.src.factorymethod.staticmethod;


/**
 * Create by zhaihongwei on 2018/3/9
 */
public class FactoryTest {

    public static void main(String[] args) {
        People chinese = PeopleFactory.createChinese();
        chinese.say();
    }
}
