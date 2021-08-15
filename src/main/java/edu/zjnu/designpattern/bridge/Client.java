package edu.zjnu.designpattern.bridge;

/**
 * Create by zhaihongwei on 2018/3/20
 */
public class Client {

    public static void main(String[] args) {
        SourceA sourceA = new SourceA();
        Bridge bridge = new Bridge(sourceA);
        bridge.method();
        System.out.println("-----------------------------------------");
        SourceB sourceB = new SourceB();
        bridge = new Bridge(sourceB);
        bridge.method();
    }
}
