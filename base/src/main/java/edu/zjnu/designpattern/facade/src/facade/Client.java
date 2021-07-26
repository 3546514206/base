package edu.zjnu.designpattern.facade.src.facade;

/**
 * Create by zhaihongwei on 2018/3/19
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("没有外观对象***********");
        ModuleA moduleA = new ModuleA();
        ModuleB moduleB = new ModuleB();
        ModuleC moduleC = new ModuleC();
        moduleA.doAThing();
        moduleB.doBThing();
        moduleC.doCThing();
        System.out.println("-----------------------------------------------");
        System.out.println("有外观对象*************");
        Facade facade = new Facade();
        facade.doSomething();
    }
}
