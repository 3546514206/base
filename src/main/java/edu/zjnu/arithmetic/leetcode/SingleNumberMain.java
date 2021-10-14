package edu.zjnu.arithmetic.leetcode;

/**
 * @description: SingleNumberMain
 * @author: 杨海波
 * @date: 2021-10-14
 **/
public class SingleNumberMain {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));

    }

    public static int singleNumber(int[] nums) {
        int rs = 0;
        for (int num : nums) {
            rs = rs ^ num;
        }

        return rs;
    }
}
