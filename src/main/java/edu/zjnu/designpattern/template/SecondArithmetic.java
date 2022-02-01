package edu.zjnu.designpattern.template;

/**
 * @description: 第二个算法
 * @author: 杨海波
 * @date: 2022-02-01
 **/
public class SecondArithmetic extends ArithmeticTemplate {
    @Override
    public void step1() {
        System.out.println("2.1");
    }

    @Override
    public void step2() {
        System.out.println("2.2");
    }

    @Override
    public void step3() {
        System.out.println("2.3");
    }

    @Override
    public boolean hook() {
        return "tag string".length() < 9;
    }
}
