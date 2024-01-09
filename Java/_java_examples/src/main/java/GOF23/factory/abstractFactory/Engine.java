package com.gqz.factory.abstractFactory;

public interface Engine {
    void run();

    void start();
}


class LuxuryEngine implements Engine {

    @Override
    public void run() {
        System.out.println("转的快！");
    }

    @Override
    public void start() {
        System.out.println("启动快!可以自动启停！");
    }

}

class LowEngine implements Engine {

    @Override
    public void run() {
        System.out.println("转的慢！");
    }

    @Override
    public void start() {
        System.out.println("启动慢!");
    }

}