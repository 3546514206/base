package edu.zjnu.base.base.jdk;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: HashMapMain
 * @author: 杨海波
 * @date: 2021-09-12
 **/
public class HashMapMain {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("0", new Person("杨海波"));
        map.put("1", new Person("吴铭铭"));
        map.put("2", new Person("王娟"));
        map.put("5", new Person("李松鹏"));
        map.put("4", new Person("梁寒"));
        map.put("3", new Person("沈馨怡"));
        map.put("6", new Person("赵美玉"));
        map.put("6", new Person("赵美美"));
        // key == null && value == null 其实没有任何效果
        //map.put(null,null);
        map.put(null,new Person("好腾达"));
        //map.put(null,null);
        map.put("7",null);
        // 结论：hashmap key value 都可以为Null，不会报错。但是value为null是实际上没有意义。
        // key为null是有意义的值。

        System.out.println(JsonPrintUtil.formatToJson(JSON.toJSON(map).toString()));
    }
}
