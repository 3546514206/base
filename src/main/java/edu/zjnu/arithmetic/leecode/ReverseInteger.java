package edu.zjnu.arithmetic.leecode;

/**
 * @description: 整数反转
 * @author: 杨海波
 * @date: 2021-08-05
 **/
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(0));
    }

    int reverse(int x) {

        if (x == 0 || x >= Integer.MAX_VALUE) {
            return 0;
        }

        int abs = Math.abs(x);
        // 123 -> 0
        StringBuilder builder = new StringBuilder();

        while (abs != 0) {
            int temp = abs % 10;
            builder.append(temp);
            abs = abs / 10;
        }

        int rs = Integer.parseInt(builder.toString());

        if (rs >= Integer.MAX_VALUE) {

        }

        return x < 0 ? -rs : rs;
    }
}

