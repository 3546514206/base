package edu.zjnu.designpattern.zhaihongwei.proxy;

/**
 * Create by zhaihongwei on 2018/3/17
 * 被代理对象
 */
public class Source implements Component {

    @Override
    public void method() {
        System.out.println("被代理对象的方法。。。");
    }
}
