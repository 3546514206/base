package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-06-26 11:18:42
 * @description: SearchInsertMain
 */
public class SearchInsertMain {

    public static void main(String[] args) {

    }

    public int searchInsert(int[] nums, int target) {
        int lIndex = 0;
        int rIndex = nums.length - 1;

        while (lIndex <= rIndex) {
            int midIndex = lIndex + (rIndex - lIndex) / 2;
            if (nums[midIndex] == target) {
                return midIndex;
            } else if (target > nums[midIndex]) {
                lIndex = midIndex + 1;
            } else if (target < nums[midIndex]) {
                rIndex = midIndex - 1;
            }
        }

        // 发现target根本不在目标中，也就是已经完全循环了一遍(left>right),这
        // 时候的left的值就是最接近target但又大于target的值（可以拿0来举例自己
        // 画一遍过程），因此return left
        return lIndex;
    }
}
