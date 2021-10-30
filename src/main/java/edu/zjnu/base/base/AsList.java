package edu.zjnu.base.base;

import java.util.Arrays;
import java.util.List;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-29
 **/
public class AsList {

    public static void main(String[] args) {
        String[] strArray = new String[2];
        List list = Arrays.asList(strArray);
        //对转换后的list插入一条数据
        //list.add("1");
        System.out.println(list);

    }
}
