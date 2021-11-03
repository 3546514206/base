package edu.zjnu.arithmetic.leetcode;

/**
 * @description: FirstUniqCharMain
 * @author: 杨海波
 * @date: 2021-11-03
 **/
public class FirstUniqCharMain {

    public static void main(String[] args) {
        //String s = "leetcode";
        String s = "loveleetcode";
        System.out.println(new FirstUniqCharMain().firstUniqChar(s));
    }

    public int firstUniqChar(String s) {
        // 计数器
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            // 计算阿斯卡的值
            int temp = s.charAt(i) - 'a';
            count[temp]++;
        }

        for (int i = 0; i < s.length(); i++) {
            int temp = s.charAt(i) - 'a';
            if (count[temp] == 1) {
                return i;
            }
        }

        return -1;

    }
}
