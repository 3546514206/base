package edu.zjnu;

import java.util.HashMap;

/**
 * @author: 杨海波
 * @date: 2023-11-06 09:08:38
 * @description: 有人相爱，有人夜里开车看海，有人力扣第一道题都做不出来。
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] rz = new Main().twoSumV2(nums, 9);
    }

    public int[] twoSum(int[] nums, int target) {

        int[] rz = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    rz[0] = i;
                    rz[1] = j;
                }
            }
        }

        return rz;
    }

    public int[] twoSumV2(int[] nums, int target) {

        int[] rz = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer index = null;
            if ((index = map.get(target - nums[i])) != null
                    && index != i) {
                rz[0] = i;
                rz[1] = index;
            }
        }

        return rz;
    }
}
