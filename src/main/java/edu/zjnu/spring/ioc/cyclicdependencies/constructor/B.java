package edu.zjnu.spring.ioc.cyclicdependencies.constructor;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class B {

    private String b;
    private A a;

    public B(A a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
