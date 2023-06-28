package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-06-28 08:55:56
 * @description: IsPalindromeMainV2
 */
public class IsPalindromeMainV2 {


    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {
        // 如果是负数，则一定不是回文数
        if (x < 0) {
            return false;
        }

        // 如果是非负数，则计算逆序数
        int cur = 0;
        int num = x;
        while (num > 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }

        return cur == x;
    }
}
