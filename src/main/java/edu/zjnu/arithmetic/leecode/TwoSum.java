package edu.zjnu.arithmetic.leecode;

import java.util.HashMap;

/**
 * @author 杨海波
 * @description 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 * <p>
 * 提示：
 * 2 <= nums.length <= 103
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * @date 2021-02-15 13:55
 */
class TwoSum {


    /**
     * 暴力方法
     *
     * @param nums
     * @param target
     * @return
     */
    int[] violence(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        return result;
    }

    /**
     * 遍历与设置哈希表
     *
     * @param nums
     * @param target
     * @return
     */
    int[] hashMapTwoTimes(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            //map::containsKey 和  map::get 介于 O(1)~O(n)
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }

        return null;
    }

    /**
     * 双指针，复杂度：一次遍历，O(N)
     */
    private int[] twoPoint(int[] nums, int target) {

        int[] index = new int[2];

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (nums[i] + nums[j] == target) {
                index[0] = i;
                index[1] = j;
                break;
            }
            if (nums[i] + nums[j] < target) {

            }

        }

        return index;
    }
}