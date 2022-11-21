package edu.zjnu.base.base.oop.protecd;

/**
 * @author: 杨海波
 * @date: 2022-11-21 22:33:48
 * @description: todo
 */
public class Main {

    public static void main(String[] args) {
        Son son = new Son();
//        Java 中可以通过对象直接访问 protected 属性，但是 C++ 中不可以，C++中只能通过 public 方法访问 protected 属性或者在类内访问
//        类内访问就包括public访问或者构造方法访问等（定义在类内的方法）
        System.out.println(son.b);
    }
}
