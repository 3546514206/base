package com.gqz.singleton;

/**
 * 测试枚举式实现单例模式(没有延时加载)
 *
 * @author ganquanzhong
 * @ClassName: SingletonDemo5
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月19日 下午1:42:27
 */
public enum SingletonByEnum {

    //实例对象  这个枚举元素，本身就是单例对象！
    //没有延时加载！
    INSTANCE;

    //添加自己需要的操作！
    public void singletonOperation() {

    }


}
