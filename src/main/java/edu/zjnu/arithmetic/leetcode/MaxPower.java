package edu.zjnu.arithmetic.leetcode;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-12-01
 **/
public class MaxPower {

    public static void main(String[] args) {
        System.out.println(new MaxPower().maxPower("leetcode"));
    }

    public int maxPower(String s) {

        int res = 0, i = 0, j = 0;

        while (j < s.length()) {

            if (s.charAt(i) == s.charAt(j)) {

                res = Math.max(res, j - i + 1);
                j++;
            } else {

                i = j;
            }
        }
        return res;

    }
}
