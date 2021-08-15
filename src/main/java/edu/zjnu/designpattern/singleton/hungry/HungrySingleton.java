package edu.zjnu.designpattern.singleton.hungry;

/**
 * Create by zhaihongwei on 2018/3/12
 */
public class HungrySingleton {

    // 饿汉模式在类加载的时候，对象就已经被实例化
    private static HungrySingleton hungrySingleton = new HungrySingleton();

    // 私有化构造方法，防止外部实例化
    private HungrySingleton() {
    }

    // 获取实例的方法
    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
