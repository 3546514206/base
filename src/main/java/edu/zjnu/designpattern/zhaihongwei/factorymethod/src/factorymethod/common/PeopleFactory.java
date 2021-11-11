package edu.zjnu.designpattern.zhaihongwei.factorymethod.src.factorymethod.common;

/**
 * Create by zhaihongwei on 2018/3/9
 */
public class PeopleFactory {

    public People everyOneSay(String everyOne) {
        if ("Chinese".equals(everyOne)) {
            return new Chinese();
        } else if ("American".equals(everyOne)) {
            return new American();
        } else {
            System.out.println("没有这个国家的人");
            return null;
        }
    }
}
