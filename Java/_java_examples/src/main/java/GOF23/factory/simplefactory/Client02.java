package com.gqz.factory.simplefactory;

/**
 * 简单工厂情况下
 *
 * @author 尚学堂高淇 www.sxt.cn
 */
public class Client02 {   //调用者

    public static void main(String[] args) {
        Car c1 = CarFactory.createCar("奥迪");
        Car c2 = CarFactory.createCar("比亚迪");

        c1.run();
        c2.run();

    }
}
