package com.java8.scopes;

import com.java8.Converter;

/**
 * @author landyl
 * @create 2:05 PM 03/01/2018
 * Accessing outer scope variables from lambda expressions is very similar to anonymous objects.
 * You can access final variables from the local outer scope as well as instance fields and static variables.
 */
public class LambdaScopesTest {
    public static void main(String[] args) {
        //We can read final local variables from the outer scope of lambda expressions:
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        stringConverter.convert(2);     // 3

        // But different to anonymous objects the variable num does not have to be declared final.
        // This code is also valid:
        //However num must be implicitly final for the code to compile. The following code does not compile:
//        int number = 1;
//        Converter<Integer, String> stringConverter =
//                (from) -> String.valueOf(from + number);
//
//        stringConverter.convert(2);     // 3

//        Writing to num from within the lambda expression is also prohibited.
//        final int number = 1;
//        Converter<Integer, String> stringConverter =
//                (from) -> String.valueOf(from + number);
//        number = 3;

    }
}
