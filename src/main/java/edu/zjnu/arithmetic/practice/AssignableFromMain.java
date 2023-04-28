package edu.zjnu.arithmetic.practice;


import java.util.HashMap;
import java.util.Map;

/**
 * @author: 杨海波
 * @date: 2023-04-27 13:00:09
 * @description: todo
 */
public class AssignableFromMain {

    public static void main(String[] args) {
        Map<String,Object> v1 = new HashMap<>();
        String v2 = "";
        System.out.println(v1.getClass().isAssignableFrom(v2.getClass()));
        System.out.println(v1.getClass().isAssignableFrom(v2.getClass()) || v2.getClass().isAssignableFrom(v1.getClass()));
    }
}
