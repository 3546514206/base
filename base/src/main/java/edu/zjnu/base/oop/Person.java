package edu.zjnu.base.oop;

/**
 * @description: Person
 * @author: 杨海波
 * @date: 2021-08-13
 **/
public class Person extends Animal {

    protected static final String PERSON_STATIC_ATTRIBUTE = "PERSON_STATIC_ATTRIBUTE";

    static {
        System.out.println("Person static block");
    }

    public Person() {
        System.out.println("Person constructor");
    }

    @Override
    public void eat() {
        System.out.println("person eating by " + Eat.BY);
    }
}
