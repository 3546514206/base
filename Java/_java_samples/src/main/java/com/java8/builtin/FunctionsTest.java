package com.java8.builtin;

import java.util.function.Function;

/**
 * @author landyl
 * @create 2:30 PM 03/01/2018
 * Functions accept one argument and produce a result.
 * Default methods can be used to chain multiple functions together (compose, andThen).
 */
public class FunctionsTest {
    public static void main(String[] args) {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        String result = backToString.apply("123");     // "123"
        System.out.println(result);
    }
}
