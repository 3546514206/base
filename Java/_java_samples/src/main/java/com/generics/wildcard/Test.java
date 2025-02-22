package com.generics.wildcard;

import com.generics.wildcard.fruit.Apple;
import com.generics.wildcard.fruit.Fruit;

/**
 * @author landyl
 * @create 9:30 AM 08/03/2018
 */
public class Test {
    public static void main(String[] args) {
        //现在我定义一个“水果盘子”，逻辑上水果盘子当然可以装苹果。
        //但实际上Java编译器不允许这个操作。会报错，“装苹果的盘子”无法转换成“装水果的盘子”。
        //error: incompatible types: Plate<Apple> cannot be converted to Plate<Fruit>
        //Plate<Fruit> p=new Plate<Apple>(new Apple());

        //下面代码就是“上界通配符（Upper Bounds Wildcards）”：Plate<？ extends Fruit>
//        翻译成人话就是：一个能放水果以及一切是水果派生类的盘子。再直白点就是：啥水果都能放的盘子。
//        这和我们人类的逻辑就比较接近了。Plate<？ extends Fruit>和Plate<Apple>最大的区别就是：
//        Plate<？ extends Fruit>是Plate<Fruit>以及Plate<Apple>的基类。直接的好处就是，我们可以用“苹果盘子”给“水果盘子”赋值了。
          Plate<? extends Fruit> p0 = new Plate<>(new Apple());
          //如果把Fruit和Apple的例子再扩展一下，食物分成水果和肉类，水果有苹果和香蕉，肉类有猪肉和牛肉，苹果还有两种青苹果和红苹果。

          //上界<? extends T>不能往里存，只能往外取
          Plate<? extends Fruit> p=new Plate<>(new Apple());


          //不能存入任何元素
//          p.set(new Fruit());    //Error
//          p.set(new Apple());    //Error

          //读取出来的东西只能存放在Fruit或它的基类里。
          Fruit newFruit1=p.get();
          Object newFruit2=p.get();
//          Apple newFruit3=p.get();    //Error

        //下界<? super T>不影响往里存，但往外取只能放在Object对象里
        //使用下界<? super Fruit>会使从盘子里取东西的get( )方法部分失效，只能存放到Object对象里。set( )方法正常。
        Plate<? super Fruit> p1=new Plate<Fruit>(new Fruit());

        //存入元素正常
        p1.set(new Fruit());
        p1.set(new Apple());

        //读取出来的东西只能存放在Object类里。
//        Apple newFruit31=p1.get();    //Error
//        Fruit newFruit11=p1.get();    //Error
        Object newFruit21=p1.get();

    }
}
