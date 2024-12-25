package com.java8;

/**
 * @author landyl
 * @create 11:31 AM 03/01/2018
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
