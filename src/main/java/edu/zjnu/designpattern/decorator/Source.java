package edu.zjnu.designpattern.decorator;

/**
 * Create by zhaihongwei on 2018/3/16
 */
public class Source implements Component {

    @Override
    public void method() {
        System.out.println("Source 实现的一些功能！");
    }
}
