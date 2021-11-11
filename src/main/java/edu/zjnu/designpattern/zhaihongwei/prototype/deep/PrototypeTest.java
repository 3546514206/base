package edu.zjnu.designpattern.zhaihongwei.prototype.deep;

import java.io.IOException;

/**
 * Create by zhaihongwei on 2018/3/14
 */
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        // 创建一个具体的需要克隆的对象
        ConcretePrototype concretePrototype = new ConcretePrototype();

        // 填充属性，方便测试
        concretePrototype.setTest1(1);
        concretePrototype.setTest2("prototype");
        Test test = new Test();
        concretePrototype.setTest(test);
        System.out.println(concretePrototype);

        // 创建Client对象，准备开始克隆
        Client client = new Client(concretePrototype);
        ConcretePrototype concretePrototypeClone = client.startClone(concretePrototype);
        System.out.println(concretePrototypeClone);

        System.out.println("克隆对象中的引用类型地址值：" + concretePrototypeClone.getTest());
        System.out.println("原对象中的引用类型地址值：" + concretePrototype.getTest());
        System.out.println("没有深克隆：" + (concretePrototypeClone.getTest() == concretePrototype.getTest()));

    }
}
