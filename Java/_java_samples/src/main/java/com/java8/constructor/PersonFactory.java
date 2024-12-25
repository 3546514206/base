package com.java8.constructor;

/**
 * @author landyl
 * @create 1:53 PM 03/01/2018
 * we specify a person factory interface to be used for creating new persons:
 */
@FunctionalInterface
public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
