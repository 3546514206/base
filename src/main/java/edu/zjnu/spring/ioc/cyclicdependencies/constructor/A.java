package edu.zjnu.spring.ioc.cyclicdependencies.constructor;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class A {

    private String a;
    private B b;

    public A(B b) {
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
