package edu.zjnu.arithmetic.leecode;

/**
 * @description: 力扣第九题
 * @author: 杨海波
 * @date: 2021-10-05
 **/
public class PalindromeMain {

    public static void main(String[] args) {
        System.out.println(new PalindromeMain().isPalindrome(121));
    }

    public boolean isPalindrome(int x) {
        //一定要保存原始值
        int original = x;

        if (x < 0) return false;

        int y = 0;

        while (x != 0) {
            int temp = x % 10;
            y = y * 10 + temp;
            x = x / 10;
        }

        return original == y;
    }
}
