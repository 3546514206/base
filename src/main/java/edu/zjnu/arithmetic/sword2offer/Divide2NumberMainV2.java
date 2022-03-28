package edu.zjnu.arithmetic.sword2offer;

/**
 * @description: Divide2NumberMainV2
 * @author: 杨海波
 * @date: 2022-03-28 18:44
 **/
public class Divide2NumberMainV2 {

    public static void main(String[] args) {
        int dividend = 15;
        int divisor = -2;
        int result = divide(dividend, divisor);
        System.out.println(result);
    }

    private static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int negative = 1;
        if (dividend < 0) {
            negative = -negative;
            dividend = -dividend;
        }

        if (divisor < 0) {
            negative = -negative;
            divisor = -divisor;
        }

        int result = doDivide(dividend, divisor);
        return result * negative;
    }

    private static int doDivide(int dividend, int divisor) {
        int result = 0;

        while (dividend >= divisor) {
            dividend = dividend - divisor;
            int index = 1;
            int temp = divisor;
            while (dividend >= (temp = temp << 2)) {
                dividend = dividend -temp;
//                todo 不会写了，明天来写
            }
            result = result + index;
        }
        return result;
    }
}
