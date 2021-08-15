package edu.zjnu.designpattern.decorator;

/**
 * Create by zhaihongwei on 2018/3/16
 * 装饰者类
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void method() {
        System.out.println("需要Source的功能！");
        component.method();
        System.out.println("这里还有一些别的功能！");
    }
}
