package com.gqz.singleton;

/**
 * 测试饿汉式单例模式
 * 在创建时立即new对象
 *
 * @author ganquanzhong
 * @ClassName: SingletonDemo1
 * @Description: 饿汉式的单例模式  静态属性就new对象
 * @date 2019年7月19日 上午10:55:50
 */
public class SingletonByHungryType {

    //类初始化时，立即加载这个对象（没有延时加载的优势）。
    //加载类时，天然的是线程安全的！  ClassLoader的加载机制 加锁同步

    //静态属性
    private static SingletonByHungryType instance = new SingletonByHungryType();

    //私有构造方法
    private SingletonByHungryType() {
    }

    //公有静态方法      方法没有同步，调用效率高！ 提供一个对外的方法，便于操作
    public static SingletonByHungryType getInstance() {
        return instance;
    }

}
