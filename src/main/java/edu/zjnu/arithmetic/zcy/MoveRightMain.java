package edu.zjnu.arithmetic.zcy;

/**
 * @author: 杨海波
 * @date: 2022-10-24 20:09:31
 * @description: 右移
 */
public class MoveRightMain {


    public static void main(String[] args) {
        int a = -1234;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a >> 2));
        System.out.println(a >> 2);
        System.out.println(Integer.toBinaryString(a >>> 2));
        System.out.println(a >>> 2);
    }
}
