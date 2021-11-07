package edu.zjnu.arithmetic.leetcode;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-11-07
 **/
public class StrStrMain {

    public static void main(String[] args) {
        System.out.println(new StrStrMain().strStr("aaaaa", "bba"));
    }

    public int strStr(String haystack, String needle) {

        if (needle.isEmpty()) {
            return 0;
        }

        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            int tempI = i;
            for (int j = 0; j < needle.length(); j++) {

                if (tempI < haystack.length() && haystack.charAt(tempI) == needle.charAt(j)) {
                    tempI++;
                } else {
                    break;
                }

                if (j == needle.length() - 1) {
                    return i;
                }
            }

        }

        return -1;
    }
}
