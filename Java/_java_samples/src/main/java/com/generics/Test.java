package com.generics;

import com.generics.wildcard.fruit.Apple;
import com.generics.wildcard.fruit.Banana;
import com.generics.wildcard.fruit.Fruit;

/**
 * @author landyl
 * @create 10:17 AM 08/03/2018
 */
public class Test {

    public static void main(String[] args) {
        ////上界<? extends T>不能往里存，只能往外取
        //不能存入任何元素
        //但是如果定了类的时候就使用<T extends Fruit>的情况是可以存入元素的

        //指定了装的是Apple，所以指定装Apple
        NewPlate<Apple> appleNewPlate = new NewPlate<>();

        appleNewPlate.set(new Apple());
        //appleNewPlate.set(new Banana());

        //指定的是Fruit，Apple跟Banana都可以装
        NewPlate<Fruit> fruitNewPlate = new NewPlate<>();

        fruitNewPlate.set(new Apple());
        fruitNewPlate.set(new Banana());


        AppleNewPlate appleNewPlate1 = new AppleNewPlate();

        appleNewPlate1.set(new Apple());
//        appleNewPlate1.set(new Banana());

    }

}
