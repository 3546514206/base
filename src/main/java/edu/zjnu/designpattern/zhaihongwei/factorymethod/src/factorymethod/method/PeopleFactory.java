package edu.zjnu.designpattern.zhaihongwei.factorymethod.src.factorymethod.method;


/**
 * Create by zhaihongwei on 2018/3/9
 */
public class PeopleFactory {

    public People createChinese() {
        return new Chinese();
    }

    public People createAmerican() {
        return new American();
    }
}
