package com.gqz.singleton;

/**
 * 需要的时候才去new.(延时加载)
 * 因为需要进行方法同步，效率低
 *
 * @author ganquanzhong
 * @ClassName: SingletonLazyLoad
 * @Description: 懒汉式 单例模式
 * @date 2019年7月19日 下午1:52:47
 */
public class SingletonLazyLoad {

    //类初始化时，不初始化这个对象（延时加载，真正用的时候再创建）。
    private static SingletonLazyLoad instance;

    private SingletonLazyLoad() { //私有化构造器
    }

    //方法同步，调用效率低！
    public static synchronized SingletonLazyLoad getInstance() {
        if (instance == null) {
            instance = new SingletonLazyLoad();
        }
        return instance;
    }

}
