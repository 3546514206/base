package edu.zjnu.designpattern.zhaihongwei.singleton.singleton.threadSafetyVolatile;

/**
 * 高效的线程安全的单例模式实现类
 *
 * @author 杨海波
 */
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

}
