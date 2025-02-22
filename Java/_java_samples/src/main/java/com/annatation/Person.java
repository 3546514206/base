package com.annatation;

/**
 * @author landyl
 * @create 2018-01-12:39 PM
 */
//在类上使用定义的Description注解
@Description(value="class annotation",count=2)
public class Person {
    private String name;
    private int age;

    //在方法上使用定义的Description注解
    @Description(desc="method annotation",count=3)
    public String speak() {
        return "speaking...";
    }
}
