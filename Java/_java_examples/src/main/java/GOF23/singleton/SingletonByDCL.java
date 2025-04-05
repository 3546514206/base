package com.gqz.singleton;

/*
 * 双重检查锁实现单例模式  double checking
 */

/**
 * @author ganquanzhong
 * @ClassName: SingletonByDCL
 * @Description: 禁止了JVM的指令重排序，防止了该问题
 * @date 2019年7月19日 下午1:57:42
 */
public class SingletonByDCL {

    /*
     * 可以使用volatile关键字来定义该变量的语义，使得每次修改instance后，
     * 线程工作内存强制刷新到主存中，
     * 禁止了JVM的指令重排序，防止了该问题
     */
    private static volatile SingletonByDCL instance = null;

    private SingletonByDCL() {

    }

    //由于编译器优化原因和JVM底层内部模型原因，执行的顺序可能会变动。偶尔出bug 使用volatile使得每次改变可见！
    public static SingletonByDCL getInstance() {
        //首先判断时候有对象，有则直接返回
        if (instance == null) {
            SingletonByDCL sc;
            synchronized (SingletonByDCL.class) {
                sc = instance;
                if (sc == null) {
                    synchronized (SingletonByDCL.class) {
                        if (sc == null) {
                            sc = new SingletonByDCL();
                        }
                    }
                    instance = sc;
                }
            }
        }
        return instance;
    }

}
