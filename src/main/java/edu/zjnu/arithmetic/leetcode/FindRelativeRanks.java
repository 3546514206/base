package edu.zjnu.arithmetic.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 简简单单的一天
 * @author: 杨海波
 * @date: 2021-12-08
 **/
public class FindRelativeRanks {

    public static void main(String[] args) {

        int[] score = new int[]{10, 3, 8, 9, 4};
        FindRelativeRanks relativeRanks = new FindRelativeRanks();
        System.out.println(relativeRanks.findRelativeRanks(score));

    }

    public String[] findRelativeRanks(int[] score) {
        //   创建一个HashMap将得分与其排名建立联系
        int len = score.length;
        // 不去改变原数组，创建一个新的数组进行排序
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = score[i];
        }
        // 对数组进行排序
        Arrays.sort(nums);
        //  创建一个HashMap建立联系
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(nums[i], len - i);
        }

        // 创建一个String[] 数组来保存数据
        String[] str = new String[len];
        // 根据score中的分数生成相应的奖励
        for (int i = 0; i < len; i++) {
            int mc = map.get(score[i]);
            if (mc == 1) {
                str[i] = "Gold Medal";
            } else if (mc == 2) {
                str[i] = "Silver Medal";
            } else if (mc == 3) {
                str[i] = "Bronze Medal";
            } else {
                str[i] = mc + "";
            }
        }

        return str;
    }
}
