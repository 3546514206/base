package edu.zjnu.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: ThreeSumMain 三数之和
 * @author: 杨海波
 * @date: 2021-10-09
 **/
public class ThreeSumMain {

    public static void main(String[] args) {
        int[] input = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(new ThreeSumMain().threeSum(input));
    }

    /**
     * 暴力
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();

        if (nums.length < 2) return list;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (0 == nums[i] + nums[j] + nums[k]) {


                        List<Integer> l = new ArrayList<>();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[k]);
                        list.add(l);
                    }
                }
            }
        }

        return list;
    }
}
