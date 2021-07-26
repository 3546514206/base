package edu.zjnu.designpattern.bridge;

/**
 * Create by zhaihongwei on 2018/3/20
 */
public class Bridge {

    private Component component;

    public Bridge(Component component) {
        this.component = component;
    }

    public void method() {
        component.method();
    }
}
