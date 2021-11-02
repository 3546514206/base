package edu.zjnu.arithmetic.leetcode;

/**
 * @description: 反转字符串
 * @author: 杨海波
 * @date: 2021-11-03
 **/
public class ReverseStringMain {

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};

        new ReverseStringMain().reverseString(s);

        for (char c : s) {
            System.out.print(c + " ");
        }
    }

    public void reverseString(char[] s) {

        for (int i = 0; i < s.length / 2; i++) {
            char tempC = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = tempC;
        }
    }
}
