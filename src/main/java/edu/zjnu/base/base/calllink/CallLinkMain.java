package edu.zjnu.base.base.calllink;

/**
 * @description: CallLinkMain
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class CallLinkMain {

    public static void main(String[] args) {
        ClassB classB = new ClassB();
        //你不好奇f1()内部调用f2()的时候执行的是哪个f2()么
        classB.f1();
    }
}
