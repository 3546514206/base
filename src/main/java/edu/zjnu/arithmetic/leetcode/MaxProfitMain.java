package edu.zjnu.arithmetic.leetcode;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-14
 **/
public class MaxProfitMain {

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {

        int rs = 0;

        if (prices.length < 2) {
            return rs;
        }

        for (int i = 0; i + 1 < prices.length; i++) {
            if (prices[i + 1] > prices[i]) {
                rs += prices[i + 1] - prices[i];
            }
        }

        return rs;
    }
}
