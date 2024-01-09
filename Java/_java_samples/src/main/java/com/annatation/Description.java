package com.annatation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author landyl
 * @create 2018-01-12:36 PM
 */
/*
 * 下面四个注解为Java5.0定义的四个标准元注解，是用来注解其他注解的
 */
//@Target说明了目前定义的注解可以使用的范围，取值常用的有如下几个：
//CONSTRUCTOR:用于描述构造器
//PACKAGE:用于描述包
//PARAMETER:用于描述参数
//METHOD:用于描述方法
//TYPE:用于描述类，接口（包括注解类型）或enum声明
//如果该注解不存在，说明定义的注解可以在任何程序元素上使用
@Target({ElementType.METHOD,ElementType.TYPE})
//@Retenttion定义了注解的生命周期，取值有如下三个：
//SOURCE:只在源文件中有效（即源文件中保留）
//CLASS:在class文件中有效（class文件中保留）
//RUNTIME:在运行时有效（一般都使用此参数，如果使用前两个则无法在运行时解析注解）
@Retention(RetentionPolicy.RUNTIME)
//@Inherited为标记注解，没有参数，加上此注解，说明当使用我们现在定义的注解的类被子类继承时，注解也会继承到子类
//@Inherited为标记注解，没有参数，加上此注解，说明当使用我们现在定义的注解的类被子类继承时，注解也会继承到子类
@Inherited
//@Documented，也是标记注解，说明定义的注解可以被javadoc等工具文档化
@Documented
//使用@interface关键字定义注解
public @interface Description {
    /*
     * 注解方法的定义（其实在注解中也可以看做成员变量）有如下的规定：
     * 1.不能有参数和抛出异常
     * 2.方法返回类型只能为八种基本数据类型和字符串，枚举和注解以及这些类型构成的数组
     * 3.可以包含默认值，通过default实现
     * 4.如果只有一个方法（成员变量），最好命名为value
     */
    @AliasFor("desc")
    String value() default "";

    int count() default 1; //默认值为1

    @AliasFor("value")
    String desc() default "";
}
