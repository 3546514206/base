package com.java8.method;

import com.java8.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author landyl
 * @create 10:58 AM 03/01/2018
 */
public class LambdaMethodTest {
    public static void main(String[] args) {
        // Method and Constructor References ×××××××××××××××××××××××××××××××××××××××

        //自定义函数式接口
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Integer converted = converter.convert("123");
//        System.out.println(converted);    // 123
        //The above example code can be further simplified by utilizing static method references:
//        Converter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("123");
//        System.out.println(converted);   // 123
        // Java 8 enables you to pass references of methods or constructors via the :: keyword.
        // The above example shows how to reference a static method. But we can also reference object methods:
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"


        // Method and Constructor References ×××××××××××××××××××××××××××××××××××××××
    }
}
