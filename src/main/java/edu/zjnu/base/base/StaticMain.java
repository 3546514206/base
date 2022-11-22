package edu.zjnu.base.base;

/**
 * @author: 杨海波
 * @date: 2022-11-22 11:00:58
 * @description: todo
 */
public class StaticMain {

    public static void main(String[] args) {
        StaticMainTest test = new StaticMainTest();
//        在 C++ 中和 Java 中，都可以通过类型或者对象访问静态成员属性，但是不推荐以对象的方式访问
        System.out.println(test.str);
        System.out.println(StaticMainTest.str);
    }
}

class StaticMainTest{
    public static final String str = "111";
}
