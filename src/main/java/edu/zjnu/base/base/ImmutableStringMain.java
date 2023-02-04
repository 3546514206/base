package edu.zjnu.base.base;

/**
 * @author: 杨海波
 * @date: 2023-02-03 13:54:53
 * @description: todo
 */
public class ImmutableStringMain {

    public static void main(String[] args) {
        String a = "a";
        System.out.println(a.hashCode());
        f(a);
        System.out.println(a.hashCode());
    }

    private static void f(String a){
        System.out.println(a.hashCode());
        a = "b";
        System.out.println(a.hashCode());
    }
}
