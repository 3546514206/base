package com.gqz.factory.abstractFactory;

public class Client {

    public static void main(String[] args) {
        //使用抽象工厂  创建一个奢华的car
        CarFactory factory = new LuxuryCarFactory();
        Engine e = factory.createEngine();
        e.run();
        e.start();

        Tyre e1 = factory.createTyre();
        e1.revolve();

    }
}
