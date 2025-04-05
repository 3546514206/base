package com.gqz.singleton;

/**
 * 测试每次获取的都是同一个对象
 *
 * @author ganquanzhong
 * @ClassName: Test
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月19日 下午1:49:55
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("==========饿汉式实现单例模式===========");
        SingletonByHungryType s1 = SingletonByHungryType.getInstance();
        SingletonByHungryType s2 = SingletonByHungryType.getInstance();
        //获取的都是同一个对象
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);

        System.out.println("==========懒汉式实现单例模式===========");
        SingletonLazyLoad s3 = SingletonLazyLoad.getInstance();
        SingletonLazyLoad s4 = SingletonLazyLoad.getInstance();
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s3 == s4);

        System.out.println("==========双重锁实现单例模式===========");
        SingletonByDCL s5 = SingletonByDCL.getInstance();
        SingletonByDCL s6 = SingletonByDCL.getInstance();
        System.out.println(s5);
        System.out.println(s6);
        System.out.println(s5 == s6);

        System.out.println("==========static inner 静态内部类实现单例模式===========");
        SingletonStaticInnerClass s7 = SingletonStaticInnerClass.getInstance();
        SingletonStaticInnerClass s8 = SingletonStaticInnerClass.getInstance();
        System.out.println(s7);
        System.out.println(s8);
        System.out.println(s7 == s8);


        System.out.println("==========enum枚举单例模式===========");
        System.out.println(SingletonByEnum.INSTANCE == SingletonByEnum.INSTANCE);


    }
}
