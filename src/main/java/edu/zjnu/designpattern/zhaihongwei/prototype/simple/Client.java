package edu.zjnu.designpattern.zhaihongwei.prototype.simple;

/**
 * Create by zhaihongwei on 2018/3/14
 */
public class Client {

    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public Prototype startClone(Prototype concretePrototype) {
        return (Prototype) concretePrototype.clone();
    }
}
