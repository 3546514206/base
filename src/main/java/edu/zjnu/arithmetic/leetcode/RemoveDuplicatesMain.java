package edu.zjnu.arithmetic.leetcode;

import java.util.Arrays;

/**
 * @description: 力扣26题目
 * @author: 杨海波
 * @date: 2021-10-12
 **/
public class RemoveDuplicatesMain {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(Arrays.toString(nums));
        System.out.println(new RemoveDuplicatesMain().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length < 1) return 0;

        int k = 0; // 数组的有效位置
        nums[k] = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (nums[k] != nums[i]) {
                k = k + 1;
                nums[k] = nums[i];
            }
        }

        return k + 1;
    }
}
