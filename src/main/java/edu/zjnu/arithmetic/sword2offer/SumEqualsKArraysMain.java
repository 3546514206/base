package edu.zjnu.arithmetic.sword2offer;

/**
 * @author: 杨海波
 * @date: 2022-11-15 09:54:46
 * @description: SumEqualsKArraysMain
 */
public class SumEqualsKArraysMain {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 5};
        System.out.println(sumEqualsKArraysMain(numbers,5));
    }

    private static int sumEqualsKArraysMain(int[] numbers, int k) {
        if (numbers.length == 0) return 0;

        int rz = 0;
        int[] sum = new int[numbers.length];

        sum[0] = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            sum[i] = sum[i - 1] + numbers[i];
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (sum[j] == sum[i] - k) {
                    rz++;
                }
            }
        }

        return rz;
    }
}
