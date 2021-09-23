package edu.zjnu.base.base;

/**
 * @description: todo
 * @author:
 * @date: 2021-09-23
 **/
public class IntByteMain {
    public static void main(String[] args) {
        int i = 0x1111;
        System.out.println((byte) i);

        byte a = 0b11101; // should be 29
        System.out.println(a);
    }
}
