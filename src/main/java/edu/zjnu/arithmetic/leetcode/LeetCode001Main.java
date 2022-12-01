package edu.zjnu.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 杨海波
 * @date: 2022-12-01 10:27:05
 * @description: todo
 */
public class LeetCode001Main {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        LeetCode001Main code001Main = new LeetCode001Main();
        int[] nums = {2, 7, 11, 15};
        int[] rz = code001Main.twoSumV2(nums, 9);
        System.out.println(rz);
    }

    public int[] twoSumV2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }

        return null;
    }
}
