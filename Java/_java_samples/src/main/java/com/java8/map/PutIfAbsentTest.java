package com.java8.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author landyl
 * @create 4:38 PM 03/02/2018
 */
public class PutIfAbsentTest {
    public static void main(String[] args) {
        //测试一下HashMap.putIfAbsent()
        HashMap<String, Object> hashMap = new HashMap<>();
        Object obj = hashMap.putIfAbsent("A", null);
        if (obj == null) {
            System.out.println("Shit : NPE!");
        }
        hashMap.putIfAbsent("A",16);
        hashMap.putIfAbsent("A",17);
        hashMap.putIfAbsent("B", 100);
        hashMap.putIfAbsent("B", 150);
        Integer i = (Integer)hashMap.putIfAbsent("B", 200);
        hashMap.put("C", 222);
        hashMap.put("C", 23);
        System.out.println(hashMap);
        System.out.println(i);

        //测试一下currentHashMap.putIfAbsent()

        Map<Long, String> clientMap = new ConcurrentHashMap<>();

        System.out.println("首先打印空的clientMap");
        System.out.println("clientMap: " + clientMap);
        System.out.println();

        //在空的clientMap中添加一个新的记录
        System.out.println("在空的clientMap中添加一个新的记录");
        System.out.println("添加之前的clientMap: " + clientMap);
        long netId = 1234567L;
        String str1 = "michael";
        String result = clientMap.putIfAbsent(netId, str1);
        System.out.println("添加之后的clientMap: " + clientMap);
        System.out.println("查看返回值result: " + result);
        System.out.println();

        //重复添加
        System.out.println("重复添加上一次的记录");
        System.out.println("添加之前的clientMap: " + clientMap);
        String result2 = clientMap.putIfAbsent(netId, str1);
        System.out.println("添加之后的clientMap: " + clientMap);
        System.out.println("查看返回值result: " + result2);
        System.out.println();

    }
}
