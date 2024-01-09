package com.generics.wildcard;

/**
 * 有一个最简单的容器：Plate类。盘子里可以放一个泛型的“东西”。我们可以对这个东西做最简单的“放”和“取”的动作：set( )和get( )方法。
 * @author landyl
 * @create 9:29 AM 08/03/2018
 */
class Plate<T> {
    private T item;
    public Plate(T t){item=t;}

    public void set(T t){item=t;}
    public T get(){return item;}
}
