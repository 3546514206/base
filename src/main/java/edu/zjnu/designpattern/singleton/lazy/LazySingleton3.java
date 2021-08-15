package edu.zjnu.designpattern.singleton.lazy;


/**
 * Create by zhaihongwei on 2018/3/12
 */
public class LazySingleton3 {

    // 懒汉模式，在类加载的时候并不实例化对象，而是在第一次调用获取实例化方法的时候实例化对象
    private static LazySingleton3 lazySingleton = null;

    // 私有化构造方法，防止外部实例化
    private LazySingleton3() {
    }

    // 获取对象实例的的方法
    public static LazySingleton3 getInstance() {

        // 判断实例是否为空，为空则创建实例
        if (lazySingleton == null) {
            synchronized (lazySingleton) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton3();
                }
            }
        }

        // 不为空，说明不是第一次调用获取实例的方法，直接返回实例。
        return lazySingleton;
    }
}
