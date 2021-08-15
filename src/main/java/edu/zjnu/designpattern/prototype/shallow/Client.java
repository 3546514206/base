package edu.zjnu.designpattern.prototype.shallow;

/**
 * Create by zhaihongwei on 2018/3/14
 */
public class Client {

    private ConcretePrototype concretePrototype;

    public Client(ConcretePrototype concretePrototype) {
        this.concretePrototype = concretePrototype;
    }

    public ConcretePrototype startClone(ConcretePrototype concretePrototype) throws CloneNotSupportedException {
        return (ConcretePrototype) concretePrototype.clone();
    }
}
