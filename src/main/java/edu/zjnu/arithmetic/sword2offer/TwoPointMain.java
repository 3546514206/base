package edu.zjnu.arithmetic.sword2offer;

/**
 * @author: 杨海波
 * @date: 2022-11-08 11:29:28
 * @description: 双指针法
 */
public class TwoPointMain {

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 4, 6, 10};
        int target = 8;

        int[] twoIndex = twoSum(numbers, target);
        for (int index : twoIndex) {
            System.out.println(index);
        }
    }

    private static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while (i < j && numbers[i] + numbers[j] != target) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[]{i, j};
    }
}
