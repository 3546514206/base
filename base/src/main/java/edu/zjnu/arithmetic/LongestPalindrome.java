package edu.zjnu.arithmetic;

/**
 * @author 杨海波
 * @description 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 * @date 2021-02-19 11:46
 */
class LongestPalindrome {

    private static final char SPACER = '#';

    public static void main(String[] args) {
        System.out.println((new LongestPalindrome()).violence("vaomxdtiuwqlwhgutkhxxhccsgvyoaccuicgybnqnslogtqhblegfudagpxfvjdacsxgevvepuwthdtybgflsxjdmmfumyqgpxatvdypjmlapccaxwkuxkilqqgpihyepkilhlfkdrbsefinitdcaghqmhylnixidrygdnzmgubeybczjceiybowglkywrpkfcwpsjbkcpnvfbxnpuqzhotzspgebptnhwevbkcueyzecdrjpbpxemagnwmtwikmkpqluwmvyswvxghajknjxfazshsvjkstkezdlbnkwxawlwkqnxghjzyigkvqpapvsntojnxlmtywdrommoltpbvxwqyijpkirvndwpgufgjelqvwffpuycqfwenhzrbzbdtupyutgccdjyvhptnuhxdwbmdcbpfvxvtfryszhaakwshrjseonfvjrrdefyxefqfvadlwmedpvnozobftnnsutegrtxhwitrwdpfienhdbvvykoynrsbpmzjtotjxbvemgoxreiveakmmbbvbmfbbnyfxwrueswdlxvuelbkrdxlutyukppkzjnmfmclqpkwzyylwlzsvriwomchzzqwqglpflaepoxcnnewzstvegyaowwhgvcwjhbbstvzhhvghigoazbjiikglbqlxlccrwqvyqxpbtpoqjliziwmdkzfsrqtqdkeniulsavsfqsjwnvpprvczcujihoqeanobhlsvbzmgflhykndfydbxatskf"));
        System.out.println((new LongestPalindrome()).extendCentre("vaomxdtiuwqlwhgutkhxxhccsgvyoaccuicgybnqnslogtqhblegfudagpxfvjdacsxgevvepuwthdtybgflsxjdmmfumyqgpxatvdypjmlapccaxwkuxkilqqgpihyepkilhlfkdrbsefinitdcaghqmhylnixidrygdnzmgubeybczjceiybowglkywrpkfcwpsjbkcpnvfbxnpuqzhotzspgebptnhwevbkcueyzecdrjpbpxemagnwmtwikmkpqluwmvyswvxghajknjxfazshsvjkstkezdlbnkwxawlwkqnxghjzyigkvqpapvsntojnxlmtywdrommoltpbvxwqyijpkirvndwpgufgjelqvwffpuycqfwenhzrbzbdtupyutgccdjyvhptnuhxdwbmdcbpfvxvtfryszhaakwshrjseonfvjrrdefyxefqfvadlwmedpvnozobftnnsutegrtxhwitrwdpfienhdbvvykoynrsbpmzjtotjxbvemgoxreiveakmmbbvbmfbbnyfxwrueswdlxvuelbkrdxlutyukppkzjnmfmclqpkwzyylwlzsvriwomchzzqwqglpflaepoxcnnewzstvegyaowwhgvcwjhbbstvzhhvghigoazbjiikglbqlxlccrwqvyqxpbtpoqjliziwmdkzfsrqtqdkeniulsavsfqsjwnvpprvczcujihoqeanobhlsvbzmgflhykndfydbxatskf"));
        System.out.println((new LongestPalindrome()).extendCentreV2("abcdefrg"));
        System.out.println((new LongestPalindrome()).manacher("abcdefrg"));
    }


    /**
     * 暴力法:会超出lecode时间限制
     *
     * @param s
     * @return
     */
    String violence(String s) {

        String temp = null;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {

                if (temp == null) temp = "";

                if (s.substring(i, j).length() >= temp.length() && isPalindrome(s.substring(i, j))) {
                    temp = s.substring(i, j);
                }
            }
        }

        return temp;
    }

    /**
     * 判断str是不是回文串
     *
     * @param str
     * @return
     */
    private boolean isPalindrome(String str) {

        char[] arr = str.toCharArray();
        int head = 0, tail = arr.length - 1;
        char cHead, cTail;

        while (head <= tail) {
            cHead = arr[head];
            cTail = arr[tail];

            if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                return false;
            }

            ++head;
            --tail;
        }

        return true;
    }

    /**
     * 中心扩展法（网上抄的）
     *
     * @param s
     * @return
     */
    String extendCentre(String s) {

        int length = s.length();
        if (length < 2) {
            return s;
        }

        int maxLen = 1;
        String res = s.substring(0, 1);

        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < length - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }

        return res;
    }

    private String centerSpread(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个空隙，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是任意一个字符，回文串的长度是偶数
        int len = s.length();
        int i = left;
        int j = right;

        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }

        // 跳出循环时的条件是 s.charAt(i) != s.charAt(j)，因此截断字符串两头都不能取
        return s.substring(i + 1, j);
    }

    /**
     * 马拉车算法:本质上还是空间换时间，时间复杂度是线性阶
     */
    public String manacher(String s) {

        if (null == s || s.length() < 2) return s;

        // 构造辅助字符串
        String proccesedSting = handleOriginalString(s);

        // 马拉车算法核心逻辑
        return getLongestSubString(proccesedSting);
    }

    /**
     * 辅助字符串：插入分隔符，获得新字符串
     * 例如： "abcd" -> "#a#b#c#d#"
     *
     * @param s
     * @return
     */
    private String handleOriginalString(String s) {
        // 对 null , "" ,length = 1的情况统一处理
        if (null == s || s.length() < 2) return s;

        if (s.indexOf(LongestPalindrome.SPACER) >= 0) throw new IllegalArgumentException("原始字符串中已经含有分隔符号，原始字符串处理失败");

        // 原始字符串
        char[] originalChars = s.toCharArray();
        // 处理之后的字符串
        char[] targetChars = new char[originalChars.length * 2 + 1];

        // 初始化字符数组
        for (int i = 0; i < originalChars.length; i++) {
            targetChars[i * 2 + 1] = originalChars[i];
            targetChars[i * 2] = LongestPalindrome.SPACER;
        }

        // 最后一个位置也要补上分隔符
        targetChars[targetChars.length - 1] = LongestPalindrome.SPACER;

        // 转成字符串返回
        return new String(targetChars);
    }

    private String getLongestSubString(String proccesedSting) {
        return null;
    }

    /**
     * 中心扩展
     *
     * @return
     */
    public String extendCentreV2(String s) {

        if (null == s || s.length() < 2) {
            return s;
        }

        //回文子串的最大长度，如果输入串的长度大于2，那么最小的可能是1，最大的可能是s.length()
        int subStringMaxLength = 1;
        //回文子串赋初值
        String subString = s.substring(0, subStringMaxLength);

        for (int i = 0; i < s.length() - 1; i++) {
            // 中心是下标为i的字符
            String singularCaseSubString = getLongestSubString(s, i, i);
            // 中心是下标为i和i+1两个字符
            String complexCaseSubString = getLongestSubString(s, i, i + 1);
            // 当前下标两种情况中的最大子串
            String longerSubStringInSingularComplex = singularCaseSubString.length() > complexCaseSubString.length() ? singularCaseSubString : complexCaseSubString;
            if (longerSubStringInSingularComplex.length() > subStringMaxLength) {
                // 更新最大子串和最大子串的长度
                subStringMaxLength = longerSubStringInSingularComplex.length();
                subString = longerSubStringInSingularComplex;
            }
        }

        return subString;
    }

    /**
     * @param s
     * @param left
     * @param right
     * @return
     */
    private String getLongestSubString(String s, int left, int right) {
        // 该逻辑其实就是判断s是否为回文串的核心逻辑
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }

        // 跳出循环时s.charAt(left) ！= s.charAt(right) ,所以left和rignt都不能包含
        return s.substring(left + 1, right);
    }
}
