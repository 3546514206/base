package edu.zjnu.arithmetic.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 力扣第217题
 * @author: 杨海波
 * @date: 2021-10-07
 **/
public class ContainsDuplicateMain {

    public static void main(String[] args) {

    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }

        return false;
    }
}
