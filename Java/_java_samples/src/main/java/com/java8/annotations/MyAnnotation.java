package com.java8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author landyl
 * @create 6:04 PM 03/02/2018
 * Furthermore the usage of annotations in Java 8 is expanded to two new targets:
 */
@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
public @interface MyAnnotation {
}
