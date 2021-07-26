package edu.zjnu.base.oop;

/**
 * @description: 匿名内部类使用外部类的方法
 * @author: 杨海波
 * @date: 2021-07-11
 **/
public class InnerUseOuterMethod {

    private Integer integer = 0;

    public static void main(String[] args) {
        (new InnerUseOuterMethod()).instanceInner();
    }

    public void instanceInner() {
        (new InnerInterface() {
            /**
             * 疑问，为什么匿名内部类可以这么直接引用外部类的方法呢？
             */
            @Override
            public void doSomeThingInInterFace() {
                System.out.println("do something in inner");
                outerMethod();
            }
        }).doSomeThingInInterFace();
    }

    private void outerMethod() {
        System.out.println("do something in outer");
    }
}
