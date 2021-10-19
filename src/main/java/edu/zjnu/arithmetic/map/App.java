package edu.zjnu.arithmetic.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-19
 **/
public class App {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("abc", "123");
        System.out.println(map.get("abc"));
    }
}
