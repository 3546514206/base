package edu.zjnu.designpattern.abstractfactory;

/**
 * Create by zhaihongwei on 2018/3/10
 */
public class AmericanFactory implements AbstractPeopleFactory {

    @Override
    public People createPeople() {

        return new American();
    }
}
