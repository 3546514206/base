package edu.zjnu.arithmetic.leetcode;

/**
 * @description: PlusOneMain
 * @author: 杨海波
 * @date: 2021-10-15
 **/
public class PlusOneMain {

    public static void main(String[] args) {

        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{2, 1, 4, 9};
        int[] c = new int[]{2, 9, 9};
        int[] d = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

//        int[] a1 = plusOne(a);
//        for (int i : a1) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//
//        int[] b1 = plusOne(b);
//        for (int i : b1) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//
//        int[] c1 = plusOne(c);
//        for (int i : c1) {
//            System.out.print(i + " ");
//        }
//        System.out.println();

        int[] d1 = plusOne(d);
        for (int i : d1) {
            System.out.print(i + "");
        }
    }

    public static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }

        }

        int[] rs = new int[digits.length + 1];
        rs[0] = 1;
        return rs;
    }
}
