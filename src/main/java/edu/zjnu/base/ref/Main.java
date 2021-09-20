package edu.zjnu.base.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @description: 四种引用
 * @author: 杨海波
 * @date: 2021-09-20
 **/
public class Main {

    public static void main(String[] args) {
        // s 是一个引用变量
        String s = "ABC";
        // srf本身是一个强引用，这里的强引用是指指向new String("ABC")的引用,也就是SoftReference类中的T
        // 软引用在程序内存不足时会被回收
        SoftReference<String> stringSoftReference = new SoftReference<String>("ABC");
        // 当JVM发现弱引用时就会回收
        WeakReference<String> weakReference = new WeakReference<String>("ABC");
        // 虚引用特点之一: 无法通过虚引用来获取对一个对象的真实引用。
        // 虚引用特点之二: 虚引用必须与ReferenceQueue一起使用，当GC准备回收一个对象，如
        // 果发现它还有虚引用，就会在回收之前，把这个虚引用加入到与之关联的ReferenceQueue中。
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<byte[]> reference = new PhantomReference<byte[]>(new byte[1], queue);

    }
}
