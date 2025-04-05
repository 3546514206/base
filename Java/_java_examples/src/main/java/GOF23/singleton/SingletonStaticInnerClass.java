package com.gqz.singleton;

/**
 * 测试静态内部类实现单例模式
 * <p>
 * 这种方式：线程安全，调用效率高，并且实现了延时加载！
 */
public class SingletonStaticInnerClass {

    private SingletonStaticInnerClass() {
    }

    //方法没有同步，调用效率高！
    //只有真正调用getInstance方法时，才会加载静态内部类。类加载的过程是默认的线程安全。
    public static SingletonStaticInnerClass getInstance() {
        return SingletonClassInstance.instance;
    }

    //没有静态属性  类初始化不会立即加载对象
    //静态内部类
    private static class SingletonClassInstance {
        private static final SingletonStaticInnerClass instance = new SingletonStaticInnerClass();
    }

}
