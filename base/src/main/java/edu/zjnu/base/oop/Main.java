package edu.zjnu.base.oop;

/**
 * @description: 主方法
 * @author: 杨海波
 * @date: 2021-07-26
 **/
public class Main {

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.outerFunc();

        // 需要通过外部类才能实例化一个内部类
        Outer.Inner inner = outer.new Inner();
        inner.innerFunc();
    }
}
