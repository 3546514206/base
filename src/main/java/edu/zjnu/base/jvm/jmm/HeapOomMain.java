package edu.zjnu.base.jvm.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 堆内存溢出:-Xms300m -Xmx300m
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/setsunayang/Documents/temp
 * @author:
 * @date: 2021-09-21
 **/
public class HeapOomMain {

    public static void main(String[] args) throws ClassNotFoundException {
        List<byte[]> list = new ArrayList<>();

        //int i = 0;
        while (true) {
            list.add(new byte[1024 * 1024]);
            //System.out.println(++i);
        }
    }
}
