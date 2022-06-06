package edu.zjnu.base.jvm.classloader;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * @description: CountClassLoader
 * @author: 杨海波
 * @date: 2022-06-06 17:29
 **/
public class CountClassLoader {

    public static void main(String[] args) {
        Field cl = null;
        Vector classes;
        try {
            cl = ClassLoader.class.getDeclaredField("classes");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        cl.setAccessible(true);
        try {
            classes = (Vector) cl.get(ClassLoader.getSystemClassLoader());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for (Object c : classes) {
            System.out.println(c.toString());
        }
    }
}
