package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-06-26 10:47:43
 * @description: FindMiddleIndexMain
 */
public class FindMiddleIndexMain {

    public static void main(String[] args) {
        case01();
    }

    private static void case01() {
        int[] nums = {2, 3, -1, 8, 4};
        System.out.println(new FindMiddleIndexMain().findMiddleIndex(nums));
    }


    public int findMiddleIndex(int[] nums) {
        // 求和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 记录前缀和
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
