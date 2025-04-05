package com.landy;

/**
 * @author: Landy
 * @date: 2019/4/10 22:59
 * @description:
 */
public class WildcardCharacter {

    static boolean match(String pattern, String content, int p, int c) {
        // if we reatch both end of two string, we are done
        if (pattern.length() == p && content.length() == c)
            return true;
        /* make sure that the characters after '*' are present in second string.
          this function assumes that the first string will not contain two
           consecutive '*'*/
        if (pattern.length() > p && '*' == pattern.charAt(p) && pattern.length() > p + 1 && content.length() == c)
            return false;
        // if the first string contains '?', or current characters of both
        // strings match
        if (pattern.length() > p && content.length() > c && ('?' == pattern.charAt(p) || pattern.charAt(p) == content.charAt(c)))
            return match(pattern, content, p + 1, c + 1);
        /* if there is *, then there are two possibilities
           a) We consider current character of second string
           b) We ignore current character of second string.*/
        if (pattern.length() > p && '*' == pattern.charAt(p))
            return match(pattern, content, p + 1, c) || match(pattern, content, p, c + 1);
        return false;
    }

    static void test(String pattern, String content) {
        if (null == pattern || null == content)
            System.out.println("no");
        System.out.println(match(pattern, content, 0, 0) ? "yes" : "no");
    }

    public static void main(String[] args) {
        test("g*ks", "geeks"); // Yes
        test("ge?ks*", "geeksforgeeks"); // Yes
        test("g*k", "gee");  // No because 'k' is not in second
        test("*pqrs", "pqrst"); // No because 't' is not in first
        test("abc*bcd", "abcdhghgbcd"); // Yes
        test("abc*c?d", "abcd"); // No because second must have 2 instances of 'c'
        test("*c*d", "abcd"); // Yes
        test("*?c*d", "abcd"); // Yes
        test("*?c***d", "abcd"); // Yes
        test("ge?ks**", "geeksl5"); // Yes
    }

}
