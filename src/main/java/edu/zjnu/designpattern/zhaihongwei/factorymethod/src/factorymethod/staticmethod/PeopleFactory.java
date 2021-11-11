package edu.zjnu.designpattern.zhaihongwei.factorymethod.src.factorymethod.staticmethod;


/**
 * Create by zhaihongwei on 2018/3/9
 */
public class PeopleFactory {

    public static People createChinese() {
        return new Chinese();
    }

    public static People createAmerican() {
        return new American();
    }
}
