package edu.zjnu.arithmetic.leetcode;

/**
 * @description: 力扣494
 * @author: 杨海波
 * @date: 2021-11-09
 **/
public class FindTargetSumWaysMain {

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(new FindTargetSumWaysMain().findTargetSumWays(nums, target));
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int dif = sum - target;

        if (dif < 0 || (dif % 2) != 0) {
            return 0;
        }

        int[][] dp = new int[nums.length + 1][dif / 2 + 1];

        for (int j = 0; j < dp.length; j++) {
            if (j == 0) {
                dp[0][0] = 1;
            } else {
                dp[j][0] = 0;
            }

        }

        int n = nums.length, neg = dif / 2;

        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }


        return dp[nums.length][dif / 2];
    }
}
