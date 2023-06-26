package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-06-26 10:01:06
 * @description: PivotIndexMain
 */
public class PivotIndexMain {

    public static void main(String[] args) {

    }

    public int pivotIndex(int[] nums) {
        // 先求和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int preSum = 0;
        for (int index = 0; index < nums.length; index++) {
            if ((sum - nums[index]) % 2 == 0 && (sum - nums[index]) / 2 == preSum) {
                return index;
            }

            preSum += nums[index];
        }

        return -1;
    }
}
