package edu.zjnu.arithmetic;

import java.util.ArrayList;
import java.util.List;

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

        List<StringBuilder> builders = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            builders.add(new StringBuilder());
        }

        int builderIndex = 0, flag = -1;
        for (char c : s.toCharArray()) {
            builders.get(builderIndex).append(c);
            if (builderIndex == 0 || builderIndex == numRows - 1) {
                flag = -flag;
            }
            builderIndex += flag;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : builders) {
            res.append(row);
        }

        return res.toString();
    }

    /**
     * 重写一遍
     *
     * @param s
     * @param n
     * @return
     */
    public String f(String s, int n) {

        //
        if (n < 2) {
            return s;
        }

        List<StringBuilder> builders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            builders.add(new StringBuilder());
        }


        return null;
    }
}
