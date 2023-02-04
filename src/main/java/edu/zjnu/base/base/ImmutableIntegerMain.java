package edu.zjnu.base.base;

/**
 * @author: 杨海波
 * @date: 2023-02-03 13:51:37
 * @description: todo
 */
public class ImmutableIntegerMain {

    public static void main(String[] args) {
        Integer a = 100;
        System.out.println(a.hashCode());
        f(a);
        System.out.println(a.hashCode());
    }

    private static void f(Integer a){
        System.out.println(a.hashCode());
        a = 101;
        System.out.println(a.hashCode());
    }
}
