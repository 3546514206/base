package edu.zjnu.arithmetic.leecode;

/**
 * @description: LongestCommonPrefix
 * @author: 杨海波
 * @date: 2021-09-09
 **/
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String stringA = "ab";
        String stringB = "a";

        String[] strs = new String[]{stringA, stringB};

        String rs = new LongestCommonPrefix().sulotion(strs);
        String rs1 = new LongestCommonPrefix().longestCommonPrefix(strs);

        System.out.println(rs + " : " + rs1);

    }

    /**
     * 横向扩展法
     *
     * @param strs
     * @return
     */
    public String sulotion(String[] strs) {
        if (0 == strs.length) {
            return "";
        }

        String rs = strs[0];

        for (int i = 1; i <= strs.length - 1; i++) {
            for (int j = 0; j < rs.length() && j < strs[i].length(); j++) {
                if (rs.charAt(j) != strs[i].charAt(j)) {
                    rs = rs.substring(0, j);
                }
            }
            if ("".equals(rs)) {
                break;
            }
        }

        return rs;
    }


    /**
     * 抄的解法
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);
            if (ans.equals(""))
                return ans;
        }
        return ans;
    }

}
