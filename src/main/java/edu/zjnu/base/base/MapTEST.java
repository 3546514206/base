package edu.zjnu.base.base;

import java.util.HashMap;

/**
 * @description: test map
 * @author: 杨海波
 * @date: 2021-07-27
 **/
public class MapTEST {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("name", null);
        map.put("name", "qqq");
        System.out.println(map.get("name"));

    }
}
