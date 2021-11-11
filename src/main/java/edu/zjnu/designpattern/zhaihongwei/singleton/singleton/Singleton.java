package edu.zjnu.designpattern.zhaihongwei.singleton.singleton;

/**
 * 五种单例模式的实现
 *
 * @author weijielu
 */
public class Singleton {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 静态变量初始化实现线程安全的单例模式
        edu.zjnu.designpattern.zhaihongwei.singleton.singleton.statics.Singleton instance1 =
                edu.zjnu.designpattern.zhaihongwei.singleton.singleton.statics.Singleton.getInstance();

        // 静态内部类实现的线程安全的单例模式
        edu.zjnu.designpattern.zhaihongwei.singleton.singleton.staticInnerClass.Singleton instance2 =
                edu.zjnu.designpattern.zhaihongwei.singleton.singleton.staticInnerClass.Singleton.getInstance();

        // 经典的非线程安全单例模式实现类
        edu.zjnu.designpattern.zhaihongwei.singleton.singleton.classic.Singleton instance3 =
                edu.zjnu.designpattern.zhaihongwei.singleton.singleton.classic.Singleton.getInstance();

        // 线程安全的单例模式实现类
        edu.zjnu.designpattern.zhaihongwei.singleton.singleton.threadSafety.Singleton instance4 =
                edu.zjnu.designpattern.zhaihongwei.singleton.singleton.threadSafety.Singleton.getInstance();

        // 高效的线程安全的单例模式实现类
        edu.zjnu.designpattern.zhaihongwei.singleton.singleton.threadSafetyVolatile.Singleton instance5 =
                edu.zjnu.designpattern.zhaihongwei.singleton.singleton.threadSafetyVolatile.Singleton.getInstance();
    }

}
