package edu.zjnu.designpattern.zhaihongwei.facade.src.facade;

/**
 * Create by zhaihongwei on 2018/3/19
 */
public class Facade {

    private ModuleA moduleA;
    private ModuleB moduleB;
    private ModuleC moduleC;

    public void doSomething() {
        moduleA = new ModuleA();
        moduleB = new ModuleB();
        moduleC = new ModuleC();

        moduleA.doAThing();
        moduleB.doBThing();
        moduleC.doCThing();
    }
}
