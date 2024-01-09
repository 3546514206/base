package com.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author landyl
 * @create 10:58 AM 03/01/2018
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //Let's start with a simple example of how to sort a list of strings in prior versions of Java
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        System.out.println("native:" + names);
        //Instead of creating anonymous objects all day long, Java 8 comes with a much shorter syntax, lambda expressions:
        Collections.sort(names,(String a,String b)->{
            return b.compareTo(a);
        });
        //As you can see the code is much shorter and easier to read. But it gets even shorter:
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        //For one line method bodies you can skip both the braces {} and the return keyword. But it gets even more shorter:
        Collections.sort(names, Collections.reverseOrder());
        System.out.println("reverseOrder:" + names);

        // Method and Constructor References ×××××××××××××××××××××××××××××××××××××××

        //自定义函数式接口
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123


        // Method and Constructor References ×××××××××××××××××××××××××××××××××××××××

//        Remember the formula example from the first section? Interface Formula defines a default method sqrt
//        which can be accessed from each formula instance including anonymous objects.
//        This does not work with lambda expressions.
//        Default methods cannot be accessed from within lambda expressions. The following code does not compile:
//        Formula formula = (a) -> sqrt( a * 100);

    }
}
