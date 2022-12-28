package edu.zjnu.base.base.jdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: 杨海波
 * @date: 2022-12-28 14:53:54
 * @description: 如何安全地操作集合
 */
public class SynchronizedListMain {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // 1
        List<Integer> synchronizedList = Collections.synchronizedList(list);
        // 2
        CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>();
        // 3
        // 同步机制：加锁
    }
}
