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

        s = s + "#";
        int res = 0;
        int index = 0;
        int count = 0;

        int len = s.length();

        for (int i = 0; i < len; i++) {

            if (s.charAt(i) == s.charAt(index)) {
                count++;
            } else {
                index = i;
                res = Math.max(res, count);
                count = 1;
            }


        }

        return res;
    }


    public int maxPowerV1(String s) {

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
