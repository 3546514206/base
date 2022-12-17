package edu.zjnu.arithmetic.sword2offer;

/**
 * @author: 杨海波
 * @date: 2022-12-17 17:18:09
 * @description: 最长子串
 */
public class LongestSubStringMain {

    public static void main(String[] args) {
            String s = "babcca";
            System.out.println(new LongestSubStringMain().longestSubString(s));
    }

    public int longestSubString(String s) {
        if (s.length() == 0) {
            return 0;
        }

        // 记录阿斯卡字符出现的次数
        int[] counts = new int[256];
        int longest = 1;
        // j 是头指针
        for (int i = 0, j = 0; i < s.length(); i++) {
            counts[s.charAt(i)]++;

            while (hasGreatThan1(counts)) {
                counts[s.charAt(j)]--;
                j++;
            }

            longest = Math.max(i - j + 1, longest);
        }

        return longest;
    }

    /**
     * @param counts
     * @return
     */
    private boolean hasGreatThan1(int[] counts) {
        for (int count : counts) {
            if (count >= 2) {
                return true;
            }
        }

        return false;
    }
}
