package com.java8.builtin;


import com.java8.constructor.Person;

import java.util.function.Supplier;

/**
 * @author landyl
 * @create 2:30 PM 03/01/2018
Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.
 */
public class SuppliersTest {
    public static void main(String[] args) {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person
    }
}
