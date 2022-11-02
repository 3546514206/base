package edu.zjnu.base.base.callInttefaceMethod;

/**
 * @description: 执行类
 * @author: 杨海波
 * @date: 2021-07-11
 **/
public class C extends B {

    public static void main(String[] args) {
        (new C()).fc();
    }

    public void fc() {
        f();
    }
}
