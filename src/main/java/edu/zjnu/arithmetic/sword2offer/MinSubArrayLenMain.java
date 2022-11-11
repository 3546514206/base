package edu.zjnu.arithmetic.sword2offer;

/**
 * @author: 杨海波
 * @date: 2022-11-09 19:35:54
 * @description: MinSubArrayLenMain
 */
public class MinSubArrayLenMain {

    public static void main(String[] args) {
        int[] numbers = new int[]{5, 1, 4, 3};
        int len = minSubArrayLen(7, numbers);
        System.out.println(len);

        int len2 = minSubArrayLenV2(1, numbers);
        System.out.println(len2);
    }

    private static int minSubArrayLenV2(int k, int[] numbers) {
        int leftIndex = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int rightIndex = 0; rightIndex < numbers.length; rightIndex++) {
            sum = sum + numbers[rightIndex];
            while (sum >= k) {
                min = Math.min(min, rightIndex - leftIndex + 1);
                sum = sum + numbers[leftIndex];
                leftIndex = leftIndex + 1;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private static int minSubArrayLen(int k, int[] numbers) {
        int leftIndex = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int rightIndex = 0; rightIndex < numbers.length; rightIndex++) {
            sum = sum + numbers[rightIndex];
            while (sum >= k) {
                minLength = Math.min(minLength, rightIndex - leftIndex + 1);
                sum = sum - numbers[leftIndex];
                leftIndex = leftIndex + 1;
            }
        }

        return minLength;
    }
}
