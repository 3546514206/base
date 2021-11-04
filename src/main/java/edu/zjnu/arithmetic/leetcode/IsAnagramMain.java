package edu.zjnu.arithmetic.leetcode;

/**
 * @description: 异位字符
 * @author: 杨海波
 * @date: 2021-11-04
 **/
public class IsAnagramMain {

    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        int[] sCount = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            sCount[index]++;
        }

        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            sCount[index]--;
        }

        for (int value : sCount) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagram2(String s, String t) {

        if (s.length() != t.length()) return false;

        int len = s.length();

        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }

        return true;
    }
}
