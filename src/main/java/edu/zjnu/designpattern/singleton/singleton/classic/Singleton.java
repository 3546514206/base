package edu.zjnu.designpattern.singleton.singleton.classic;

/**
 * 经典的非线程安全单例模式实现类
 *
 * @author weijielu
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
