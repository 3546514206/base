package edu.zjnu.base.base;

/**
 * @author: 杨海波
 * @date: 2023-09-06 19:07:39
 * @description: CopyOnWriteArrayListDemo
 */
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();

        // 添加元素
        list.add(1);
        list.add(2);
        list.add(3);

        // 遍历元素
        for (int num : list) {
            System.out.println(num);
        }

        // 修改元素
        list.set(1, 4);

        // 再次遍历元素
        for (int num : list) {
            System.out.println(num);
        }
    }
}
