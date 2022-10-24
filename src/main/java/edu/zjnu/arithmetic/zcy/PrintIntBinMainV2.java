package edu.zjnu.arithmetic.zcy;

/**
 * @author: 杨海波
 * @date: 2022-10-24 18:31:02
 * @description: 将十进制的整形数打印成二进制的字符串-有符号
 */
public class PrintIntBinMainV2 {

    public static void main(String[] args) {

        int a = -12345678;
        System.out.println(Integer.toBinaryString(a));
        print(a);
    }

    private static void print(int a) {
        if (a >= 0) {
            for (int i = 31; i >= 0; i--) {
                System.out.print((a & (1 << i)) > 0 ? "1" : "0");
            }
        } else {
            a = -a;
            StringBuilder stringBuilder = new StringBuilder();
            // 进位，初始值为 1 是因为负数的补码为原码的反码 + 1。
            int carry = 1;
            // 从低位开始处理，最高位是符号，不必处理，必为 1，如果有进位，则溢出。
            for (int i = 0; i < 31; i++) {

                int y = ((a & (1 << i)) > 0) ? 1 : 0;
                // 二进制取反
                y = (y + 1) % 2;

                if (y + carry > 1) {
                    y = y + carry - 2;
                    carry = 1;
                } else {
                    y = y + carry;
                    carry = 0;
                }

                stringBuilder.append(y);
            }

            stringBuilder.append(1);

            System.out.println(stringBuilder.reverse().toString());
        }

    }
}
