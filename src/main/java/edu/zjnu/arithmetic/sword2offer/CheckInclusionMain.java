package edu.zjnu.arithmetic.sword2offer;

/**
 * @author: 杨海波
 * @date: 2022-12-17 15:04:11
 * @description: 原串和变位词都是小写的
 */
public class CheckInclusionMain {

    public static void main(String[] args) {
        String s2 = "ab";
        String s1 = "a";
        System.out.println(new CheckInclusionMain().checkInclusion(s1, s2));
    }


    /**
     * @param s1 变位词
     * @param s2 原串
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        // 记录变位词中每个字符出现的次数
        int[] chars = new int[26];
        // 根据变位词初始化
        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i) - 'a']++;
            chars[s2.charAt(i) - 'a']--;
        }

        if (isAllZero(chars)) {
            return true;
        }

        for (int i = 1; i + s1.length() - 1 < s2.length(); i++) {
            chars[s2.charAt(i - 1) - 'a']++;
            chars[s2.charAt(i + s1.length() - 1) - 'a']--;
            if (isAllZero(chars)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断所有字符出现次数是不是等于0
     *
     * @param chars
     * @return
     */
    private boolean isAllZero(int[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }

        return true;
    }

}









