package edu.zjnu.base.base;

/**
 * @description: 移位运算
 * @author: 杨海波
 * @date: 2021-10-11
 **/
public class MoveMain {

    public static void main(String[] args) {
        int a = (2 ^ 16) - 1;
        System.out.println(a);

        int b = 2 ^ 16 - 1;
        System.out.println(b);

        int c = (int) Math.pow(2, 16) - 1;
        System.out.println(c);

        int d = 15;
        System.out.println(Integer.toBinaryString(d));
    }
}
