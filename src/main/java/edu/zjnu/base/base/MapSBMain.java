package edu.zjnu.base.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: 杨海波
 * @date: 2023-09-21 12:11:07
 * @description: MapSBMain
 */
public class MapSBMain {

    public static void main(String[] args) {

        String input = "a";

        Map<String, String> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");
        map.put("c", "cc");
        map.put("d", "dd");

        for (String key : map.keySet()) {
            if (Objects.equals(input, key)) {
                System.out.println("do something!");
            }
        }
    }
}
