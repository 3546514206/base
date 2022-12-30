package edu.zjnu.base.base.jvm.memoryLeak;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 杨海波
 * @date: 2022-12-30 17:10:22
 * @description: 静态集合的生命周期和 JVM 一致，所以静态集合引用的对象不能被释放。
 * 还在与c++ 不同，即便当前的方法执行完了，还是有机会释放静态几何内存的。
 */
public class StaticListMain {
    static List list = new ArrayList();

    public void oomTests(){
        Object obj = new Object();

        list.add(obj);
    }
}
