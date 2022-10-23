package edu.zjnu.arithmetic.zcy;

/**
 * @Auther: setsunayang
 * @Date: 2022-10-23 20:32:35
 * @Description: 将十进制的整形数打印成二进制的字符串
 */
public class PrintIntBin {


    public static void main(String[] args) {
        int num = 928379;
        printIntBin(num);
    }

    private static void printIntBin(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print(((num & (1 << i)) == 0) ? "0" : "1");
        }
        System.out.println("");
    }
}

