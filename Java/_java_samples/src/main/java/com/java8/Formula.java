package com.java8;

/**
 * @author landyl
 * @create 9:24 AM 03/01/2018
 * Java 8 enables us to add non-abstract method implementations to interfaces by utilizing the 'default' keyword.
 * This feature is also known as Extension Methods.
 *
 */
public interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
