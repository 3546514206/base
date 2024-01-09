package com.gqz.factory.simplefactory;

/**
 * 简单工厂类
 */
public class CarFactory2 {

    public static Car createAudi() {
        return new Audi();
    }

    public static Car createByd() {
        return new Byd();
    }

}
