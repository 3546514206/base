package edu.zjnu.designpattern.template;

/**
 * @description: 具体实现1
 * @author: 杨海波
 * @date: 2022-02-01
 **/
public class FirstArithmetic extends ArithmeticTemplate {

    @Override
    public void step1() {
        System.out.println("1.1");
    }

    @Override
    public void step2() {
        System.out.println("1.2");
    }

    @Override
    public void step3() {
        System.out.println("1.3");
    }

    @Override
    public boolean hook() {
        return "this is a string".length() > 8;
    }
}
