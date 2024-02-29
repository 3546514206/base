package edu.zjnu;

/**
 * @author 杨海波
 * @date 2024/2/29 19:59
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        try {
            f1();
        } catch (MyException myException) {
            System.out.println("myException");
        }
        System.out.println("main");
    }

    private static void f1() {
        f2();
        System.out.println("f1");
    }

    private static void f2() {
        f3();
        System.out.println("f2");
    }

    private static void f3() {
        System.out.println("f3");
        throw new MyException();
    }
}
