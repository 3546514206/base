package edu.zjnu.arithmetic.leetcode;

/**
 * @description: IntToRomanMain
 * @author: 杨海波
 * @date: 2021-10-09
 **/
public class IntToRomanMain {

    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public static void main(String[] args) {
        System.out.println(new IntToRomanMain().intToRoman(3));
        System.out.println(new IntToRomanMain().intToRoman(4));
        System.out.println(new IntToRomanMain().intToRoman(9));
        System.out.println(new IntToRomanMain().intToRoman(58));
        System.out.println(new IntToRomanMain().intToRoman(1994));
    }

    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }
}
