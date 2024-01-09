package com.generics;

import com.generics.wildcard.fruit.Fruit;

/**
 * @author landyl
 * @create 10:16 AM 08/03/2018
 */
public class NewPlate<T extends Fruit> {

    private T item;

    public void set(T t){item=t;}
    public T get(){return item;}

}
