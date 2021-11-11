package edu.zjnu.designpattern.zhaihongwei.singleton.lazy;


/**
 * Create by zhaihongwei on 2018/3/12
 */
public class LazySingleton4 {

    // 私有化构造方法，防止外部实例化
    private LazySingleton4() {
    }

    // 获取实例的方法
    public static LazySingleton4 getInstance() {
        return LazySingletonFactory.instance;
    }

    // 通过内部类的形式完成实例的创建
    private static class LazySingletonFactory {
        private static final LazySingleton4 instance = new LazySingleton4();
    }
}
