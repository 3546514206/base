package edu.zjnu.designpattern.prototype.simple;

/**
 * Create by zhaihongwei on 2018/3/14
 */
public class ConcretePrototype implements Prototype {

    // test1 和 test2 仅用来测试
    private int test1;
    private String test2;
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    @Override
    public ConcretePrototype clone() {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        concretePrototype.setTest1(this.test1);
        concretePrototype.setTest(this.test);
        concretePrototype.setTest2(this.test2);
        return concretePrototype;
    }

}
