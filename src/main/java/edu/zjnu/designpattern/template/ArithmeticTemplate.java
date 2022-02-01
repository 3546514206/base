package edu.zjnu.designpattern.template;

/**
 * @description: 定义模板方法
 * @author: 杨海波
 * @date: 2022-02-01
 **/
public abstract class ArithmeticTemplate {

    protected final void arithmeticTemp() {
        step1();
        step2();
        if (hook()) step3();
    }

    public abstract void step1();

    public abstract void step2();

    public abstract void step3();

    public abstract boolean hook();
}
