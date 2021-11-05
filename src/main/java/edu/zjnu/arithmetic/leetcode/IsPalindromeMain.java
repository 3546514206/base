package edu.zjnu.arithmetic.leetcode;

/**
 * @description: isPalindrome
 * @author: 杨海波
 * @date: 2021-11-05
 **/
public class IsPalindromeMain {

    public static void main(String[] args) {

        //String s = "A man, a plan, a canal: Panama";
        //String s = "race a car";
        //String s = "A man, a plan, a canal: Panama";
        String s = "OP";
        System.out.println(new IsPalindromeMain().isPalindrome(s));
    }

    public boolean isPalindrome(String s) {
        String lowerS = s.toLowerCase();
        System.out.println(lowerS);
        //双指针
        int lIndex = 0;
        int rIndex = s.length() - 1;

        while (lIndex < rIndex) {
            char lChar = lowerS.charAt(lIndex);
            char rChar = lowerS.charAt(rIndex);

            if ((lChar - 'a') > 25 || (lChar - 'a') < 0) {
                lIndex++;
                continue;
            }
            if ((rChar - 'a') > 25 || (rChar - 'a') < 0) {
                rIndex--;
                continue;
            }

            if (lChar == rChar) {
                lIndex++;
                rIndex--;
            } else {
                return false;
            }
        }

        return true;
    }
}
