package edu.zjnu.designpattern.zhaihongwei.prototype.deep;

import java.io.IOException;

/**
 * Create by zhaihongwei on 2018/3/14
 */
public class Client {

    private ConcretePrototype concretePrototype;

    public Client(ConcretePrototype concretePrototype) {
        this.concretePrototype = concretePrototype;
    }

    public ConcretePrototype startClone(ConcretePrototype concretePrototype) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        return (ConcretePrototype) concretePrototype.deepClone();
    }
}