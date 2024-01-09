package com.java8.builtin;


import com.java8.constructor.Person;

import java.util.function.Consumer;

/**
 * @author landyl
 * @create 2:30 PM 03/01/2018
Consumers represents operations to be performed on a single input argument.
 */
public class ConsumersTest {
    public static void main(String[] args) {
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));
    }
}
