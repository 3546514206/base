package edu.zjnu.arithmetic.leetcode;

/**
 * @description: 小青蛙 滚动数组
 * @author: 杨海波
 * @date: 2022-08-10 19:39
 **/
public class FrogMain {

    public static void main(String[] args) {
        int n = 43;
        System.out.println(new FrogMain().numWays(n));
    }

    public int numWays(int n) {
        // 动态规划
        int[] f = new int[n + 1];

        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }

        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = (f[i - 1] + f[i - 2]) % 1000000007;
        }

        return f[n];
    }
}
