package edu.zjnu.arithmetic.sword2offer;

/**
 * @author SetsunaYang
 */
public class Divide2NumberMain {

    public static void main(String[] args) {
        int a = -15;
        int b = 2;
        int rs = doDivide(a, b);
        System.out.println(rs);
    }

    private static int doDivide(int a, int b) {
        if (b == 0) {
            return 0;
        }

        boolean isNegative = a * b > 0;
        a = Math.abs(a);
        b = Math.abs(b);

        int index = 0;
        while (a > b) {
            a = a - b;
            index++;
        }


        return isNegative ? index : -index;
    }
}
