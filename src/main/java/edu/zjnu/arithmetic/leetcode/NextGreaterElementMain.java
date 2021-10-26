package edu.zjnu.arithmetic.leetcode;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-26
 **/
public class NextGreaterElementMain {


    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};

        int[] rs = nextGreaterElement(nums1, nums2);
        for (int r : rs) {
            System.out.print(r + " ");
        }

    }


    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] rs = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {

            int j = 0;
            int index = 0;
            while (nums2[j] != nums1[i]) {
                j++;
                index = j;
            }

            rs[i] = -1;
            for (int k = index +1; k < nums2.length; k++) {
                if(nums2[k] > nums1[i]){
                    rs[i] = nums2[k];
                    break;
                }
            }


        }

        return rs;
    }
}
