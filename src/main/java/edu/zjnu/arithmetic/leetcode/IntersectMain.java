package edu.zjnu.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 两个数组的交集
 * @author: 杨海波
 * @date: 2021-10-14
 **/
public class IntersectMain {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        int[] rs = intersect(nums1, nums2);
        for (int r : rs) {
            System.out.print(r + " ");
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                ++i;
                ++j;
                continue;
            }
            if (nums1[i] > nums2[j]) {
                ++j;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                ++i;
            }
        }

        int[] rs = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            rs[k] = list.get(k);
        }

        return rs;
    }
}
