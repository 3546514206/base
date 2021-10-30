package edu.zjnu.arithmetic.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-29
 **/
public class SortUtil {


    /**
     *
     * @param treeMap
     * @return
     */
    public static TreeMap<String, Object> sortByTreeMapKey(TreeMap<String, Object> treeMap) {

        ArrayList<String> list = new ArrayList<>(treeMap.keySet());

        list.sort((value1, value2) -> {
            int len1 = value1.length();
            int len2 = value2.length();

            // 长度相等,根据每位的阿斯卡运算
            if (len1 == len2) {
                int k = 0;
                while (k < len1) {
                    char c1 = value1.charAt(k);
                    char c2 = value2.charAt(k);
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    k++;
                }
            }

            // 长度不相等的时候会执行至此,字符串长度越大的必然更大
            return value1.length() > value2.length() ? 1 : -1;

            // 标准的compare方法必须返回0，事实上永远也不可能返回0，
            // 因为treemap的key值不可能重复value2与value2也不可能相等
            // 所以无需考虑返回0的情况
            // return 0;
        });

        TreeMap<String, Object> rs = new TreeMap<>();
        for (String s : list) {
            rs.put(s,treeMap.get(s));
        }
        return rs;
    }
}
