package edu.zjnu.arithmetic;

/**
 * @description: LongestCommonPrefix
 * @author: 杨海波
 * @date: 2021-09-09
 **/
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String stringA = "asdwian";
        String stringB = "asdeian";
        String stringC = "asdwran";
        String[] strs = new String[]{stringA, stringB, stringC};

        String rs = new LongestCommonPrefix().longestCommonPrefix(strs);

        System.out.println(rs);

    }

    /**
     * 
     * @param strs
     * @return
     */
    public String sulotion(String[] strs) {

        return null;
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
