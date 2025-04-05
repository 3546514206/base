package com.java8.annotations;

import java.lang.annotation.Repeatable;

/**
 * @author landyl
 * @create 5:50 PM 03/02/2018
 */
@Repeatable(Hints.class)
public @interface Hint {
    String value();
}
