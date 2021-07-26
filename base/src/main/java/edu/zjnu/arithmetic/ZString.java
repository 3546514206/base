package edu.zjnu.arithmetic;

/**
 * @description: 力扣题，Z字字符串
 * @author: 杨海波
 * @date: 2021-07-26
 **/
public class ZString {

    public static void main(String[] args) {
        System.out.println(new ZString().convert("PAYPALISHIRING", 3));
    }

    public String convert(String s, int numRows) {

        if (numRows < 2) {
            return s;
        }

        if (null == s || s.length() < 2) {
            return s;
        }

        StringBuilder[] builders = new StringBuilder[numRows];

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < builders.length; j++) {

            }
        }

        return null;
    }
}
