package edu.zjnu.arithmetic.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-19
 **/
public class App {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("abc", "123");
        System.out.println(map.get("abc"));

        TreeMap<String,Object> treeMap = new TreeMap<>();
        treeMap.put("5","test5");
        treeMap.put("1","test1");
        treeMap.put("2","test2");
        treeMap.put("2","test22");
        treeMap.put("10","test");

        treeMap = SortUtil.sortByTreeMapKey(treeMap);






    }
}
