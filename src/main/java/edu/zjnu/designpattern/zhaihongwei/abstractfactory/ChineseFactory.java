package edu.zjnu.designpattern.zhaihongwei.abstractfactory;

/**
 * Create by zhaihongwei on 2018/3/10
 */
public class ChineseFactory implements AbstractPeopleFactory {

    @Override
    public People createPeople() {

        return new Chinese();
    }
}
