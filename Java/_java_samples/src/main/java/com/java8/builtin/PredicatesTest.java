package com.java8.builtin;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author landyl
 * @create 2:20 PM 03/01/2018
Predicates are boolean-valued functions of one argument.
The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
 */
public class PredicatesTest {

    public static void main(String[] args) {
        //
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        System.out.println(nonNull.test(null));
        System.out.println(isNull.test(null));

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate(); //取相反bool值
        System.out.println(isNotEmpty.test("not empty"));
    }

}
