package edu.zjnu.arithmetic.leetcode;

import java.util.Arrays;

/**
 * @author: 杨海波
 * @date: 2023-02-04 19:43:47
 * @description: getMaximumConsecutive
 */
public class GetMaximumConsecutiveMain {

    public static void main(String[] args) {
        int[] coins = {1, 1, 1, 4};
        System.out.println(new GetMaximumConsecutiveMain().getMaximumConsecutive(coins));
    }

    public int getMaximumConsecutive(int[] coins) {
        // 排序
        Arrays.sort(coins);
        // 排完序，从 coins 数组中一个个拿硬币，可以构造的 x 是从小到大的。
        // 假设某时我们可以构造出 连续整数集 X=[a,b] 全封闭区间，当新增一个数 y （y的索引是此时手中硬币最大索引 + 1）时，我们呢一定能够构造出
        // [c , d] = [a + y,b + y] 的全封闭区间。如果此时 b + 1 > = a + y,则集合 X 得到扩充。
        // 要记录|X|

        int a = 0;
        int b = 0;

        int result = 0;

        for (int coin : coins) {
            int c = a + coin;
            int d = b + coin;

            if (b + 1 >= c) {
                b = d;
                result = b - a + 1;
                continue;
            }

            // 记录 |X|
            result = b - a + 1;
            a = c;
            b = d;
        }

        return result;
    }
}
