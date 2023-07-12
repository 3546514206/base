package edu.zjnu.arithmetic.leetcode;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author: 杨海波
 * @date: 2023-07-12 09:21:46
 * @description: ReverseMain
 */
public class ReverseMain {

    public static void main(String[] args) {
            System.out.println(new ReverseMain().reverse(1534236469));
    }

    public int reverse(int x) {
        // 负数标识
        boolean tag = false;
        // 装成正数处理
        if (x < 0) {
            tag = true;
            x = x * (-1);
        }

        int ans = 0;
        while (x > 0) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int a = x % 10;
            ans = ans * 10 + a;
            x = x / 10;
        }

        if(tag){
            return ans * (-1);
        }

        return ans;
    }
}
