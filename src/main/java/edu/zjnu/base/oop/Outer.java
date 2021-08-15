package edu.zjnu.base.oop;

/**
 * @description: 外部类和内部类
 * @author: 杨海波
 * @date: 2021-07-26
 **/
public class Outer {

    public void outerFunc() {
        System.out.println("outer");
    }

    class Inner {
        public void innerFunc() {
            System.out.println("inner");
        }
    }
}
