package edu.zjnu.base.base.oop;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-15 10:39
 **/
public class ParentMain {

    public static void main(String[] args) {
        SubA subA = new SubA("1");
        SubB subB = new SubB("2");
        System.out.println(subA.value.hashCode());
        System.out.println(subB.value.hashCode());
    }
}
