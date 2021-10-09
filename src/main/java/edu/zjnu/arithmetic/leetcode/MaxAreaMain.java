package edu.zjnu.arithmetic.leetcode;

/**
 * @description: MaxAreaMain:力扣11题
 * @author: 杨海波
 * @date: 2021-10-09
 **/
public class MaxAreaMain {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new MaxAreaMain().maxArea(height));
    }

    private int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int size = 0;

        while (l < r) {

            int tempSize = (Math.min(height[l], height[r])) * (r - l);
            size = Math.max(tempSize, size);

            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }

        return size;
    }
}
