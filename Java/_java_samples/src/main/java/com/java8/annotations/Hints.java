package com.java8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author landyl
 * @create 5:50 PM 03/02/2018
 * Java 8 enables us to use multiple annotations of the same type by declaring the annotation @Repeatable.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Hints {
    Hint[] value();
}
