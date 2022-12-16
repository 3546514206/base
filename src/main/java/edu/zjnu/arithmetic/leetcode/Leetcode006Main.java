package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2022-12-12 16:42:29
 * @description: todo
 */
public class Leetcode006Main {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        String rz = new Leetcode006Main().convert(s, 3);
        System.out.println(rz);
    }

    public String convert(String s, int numRows) {
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        int flag = -1;
        char[] chars = s.toCharArray();
        int builderIndex;
        for (int i = 0; i < chars.length; i++) {
            if (i % numRows == 0) {
                flag = -flag;
            }

            if (flag == 1) {
                builderIndex = i % numRows;
            } else {
                builderIndex = numRows - i % numRows;
            }
            builders[builderIndex].append(chars[i]);
        }

        return null;
    }
}
