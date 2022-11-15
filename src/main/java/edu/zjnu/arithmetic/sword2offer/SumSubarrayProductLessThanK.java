package edu.zjnu.arithmetic.sword2offer;

/**
 * @author: 杨海波
 * @date: 2022-11-14 10:01:04
 * @description: SumSubarrayProductLessThanK
 */
public class SumSubarrayProductLessThanK {

    public static void main(String[] args) {
        int[] numbers = {10, 5, 2, 6};
        int k = 100;
        int sum = sumSubarrayProductLessThanKV2(numbers, k);
        System.out.println(sum);
    }

    private static int sumSubarrayProductLessThanK(int[] numbers, int k) {
        int left = 0;
        int count = 0;
        int mul = 1;

        for (int right = 0; right < numbers.length; right++) {
            mul = mul * numbers[right];
            while (left <= right && mul >= k) {
                mul = mul / numbers[left];
                left++;
            }
            count = count + (right >= left ? right - left + 1 : 0);
        }

        return count;
    }

    private static int sumSubarrayProductLessThanKV2(int[] numbers, int k) {
        int count = 0;
        for (int right = 0; right < numbers.length; right++) {
            int mul = 1;
            for (int left = 0; left <= right; left++) {
                mul *= numbers[left];
                if (mul < k) {
                    count++;
                }
            }
        }

        return count;
    }
}
