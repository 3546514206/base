package edu.zjnu.base.base.oop;

/**
 * @description: Animal
 * @author: 杨海波
 * @date: 2021-08-13
 **/
public abstract class Animal implements Eat {

    protected static final String ANIMAL_STATIC_ATTRIBUTE = "ANIMAL_STATIC_ATTRIBUTE";

    static {
        System.out.println("animal static block");
    }

    public Animal() {
        System.out.println("animal constructor");
    }

    protected void f() {
        System.out.println("animal method f()");
    }

}
