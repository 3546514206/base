package edu.zjnu.base.base.calllink;

/**
 * @description: ClassA
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class ClassA {

    public void f1() {
        System.out.println("f1 in ClassA");
        f2();
    }

    public void f2() {
        System.out.println("f2 in ClassA");
    }
}
