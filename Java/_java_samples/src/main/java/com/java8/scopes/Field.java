package com.java8.scopes;

import com.java8.Converter;

/**
 * @author landyl
 * @create 2:11 PM 03/01/2018
 * In constrast to local variables we have both read and write access to instance fields and static variables
 * from within lambda expressions.
 * This behaviour is well known from anonymous objects.
 */
public class Field {
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

}
