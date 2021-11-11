package edu.zjnu.designpattern.zhaihongwei.abstractfactory;

/**
 * Create by zhaihongwei on 2018/3/10
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        AbstractPeopleFactory factory = new ChineseFactory();
        People people = factory.createPeople();

        people.say();
    }
}
