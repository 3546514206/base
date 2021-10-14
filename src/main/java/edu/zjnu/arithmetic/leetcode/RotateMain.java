package edu.zjnu.arithmetic.leetcode;

/**
 * @description: 旋转数组
 * @author: 杨海波
 * @date: 2021-10-14
 **/
public class RotateMain {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void rotate(int[] nums, int k) {
        int[] tempNums = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            tempNums[(i + k) % nums.length] = nums[i];
        }

        System.arraycopy(tempNums, 0, nums, 0, tempNums.length);
    }
}
