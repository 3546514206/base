package edu.zjnu.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 杨海波
 * @date: 2022-12-01 18:03:21
 * @description: todo
 */
public class Leetcode003Main {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(new Leetcode003Main().lengthOfLongestSubstringV2(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int left = 0;
        int right;
        int rz = 1;

        for (; left < s.length(); left++) {
            for (right = left + 1; right < s.length(); right++) {
                String substring = s.substring(left, right);

                if (substring.contains("" + s.charAt(right))) {
                    break;
                }

                rz = Math.max(right - left + 1, rz);
            }
        }

        return rz;
    }

    public int lengthOfLongestSubstringV2(String s) {
        if (s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }

        int rz = 1;
        Map<Character, Integer> map = new HashMap<>();
        int lIndex = 0;
        int rIndex;

        for (; lIndex < s.length(); lIndex++) {
            map.put(s.charAt(lIndex), lIndex);
            for (rIndex = lIndex + 1; rIndex < s.length(); rIndex++) {
                if (map.containsKey(s.charAt(rIndex))) {
                    rz = Math.max((rIndex - lIndex), rz);
                    lIndex = map.get(s.charAt(rIndex));
                    break;
                }
                map.put(s.charAt(rIndex), lIndex);
            }
            map.clear();
//             如果基于当前左指针的位置，当前右指针能挪到最后一位，那么左指针就没有必要往后挪了
            if (rIndex == s.length()) {
                rz = Math.max((rIndex - lIndex), rz);
                break;
            }
        }


        return rz;
    }
}
