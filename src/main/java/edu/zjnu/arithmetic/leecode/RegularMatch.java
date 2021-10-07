package edu.zjnu.arithmetic.leecode;

/**
 * @description: RegularMatch 力扣第十题
 * @author: 杨海波
 * @date: 2021-10-07
 **/
public class RegularMatch {

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        // 如果P为空，只要再看看S即可
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // 首元素是否匹配
        boolean matched = (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || s.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (matched && isMatch(s.substring(1), p));
        }

        // 一般情况
        else {
            return matched && isMatch(s.substring(1), p.substring(1));
        }

    }
}

















