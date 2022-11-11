package edu.zjnu.base.base;

/**
 * @author: 杨海波
 * @date: 2022-10-24 20:09:31
 * @description: 右移
 */
public class MoveRightMain {


    public static void main(String[] args) {
        int a = -1024;
        System.out.println(Integer.toBinaryString(a));
        // 有符号右移
        int b = a >> 2;
        // 无符号右移
        int c = a >>> 2;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
