package edu.zjnu.arithmetic.leetcode;

import java.util.Arrays;

/**
 * @description: 力扣第455题
 * @author: 杨海波
 * @date: 2021-10-07
 **/
public class FindContentChildren {

    public static void main(String[] args) {
        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 1};
        System.out.println(findContentChildren(g, s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int rs = 0;
        int index = s.length - 1;

        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && g[i] <= s[index]) {
                index--;
                rs++;
            }

        }

        return rs;
    }
}
