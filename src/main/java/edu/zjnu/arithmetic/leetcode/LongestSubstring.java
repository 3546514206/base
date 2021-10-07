package edu.zjnu.arithmetic.leetcode;

/**
 * @author 杨海波
 * @description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * @date 2021-02-17 16:02
 */
class LongestSubstring {

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    int lengthOfLongestSubstring(String s) {
        int len = s.length();

        //空字符串没有子串
        if (len == 0) {
            return 0;
        }

        int[] window = new int[128];
        int left = 0, right = 0;
        int size = 0;

        while (right < len) {
            char c = s.charAt(right);
            while (window[c] > 0) {
                window[s.charAt(left)]--;
                left++;
            }
            window[c]++;
            size = Math.max(size, right - left + 1);
            right++;
        }
        return size;
    }

    /**
     * 暴力方法（超时了）：
     * 假设存在判断字符串是否唯一的方法isUnique(String sub)，返回结果为size;
     * 找出所有的子串，子串调用isUnique方法视实际情况不断更新size
     *
     * @param s
     * @return
     */
    int lengthOfLongestSubstringInViolence(String s) {
        int size = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isUnique(s.substring(i, j + 1))) {
                    size = Math.max(size, j - i + 1);
                }
            }
        }

        return size;
    }

    /**
     * @param sub 非空子串
     * @return
     */
    private boolean isUnique(String sub) {
        char[] chars = sub.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    //非唯一字符串
                    return false;
                }
            }
        }

        return true;
    }
}
