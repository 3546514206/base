package edu.zjnu.arithmetic.sword2offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 杨海波
 * @date: 2022-11-08 15:05:22
 * @description: 三数之和
 */
public class ThreeSumMain {

    public static void main(String[] args) {
        int[] numbers = new int[]{-5, -2, -1, -1, 0, 1, 2, 2, 3, 4, 7};
        List<List<Integer>> results = threeSum(numbers);

        for (List<Integer> result : results) {
            System.out.println(result);
        }
    }

    private static List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> results = new ArrayList<>();
        if (numbers.length >= 3) {
            // Arrays.sort(numbers);

            int i = 0;
            while (i < numbers.length - 2) {
                // 寻找 -numbers[i] = numbers[j] + numbers[k]
                twoSum(numbers, i, results);
                ++i;
            }
        }

        return results;
    }

    private static void twoSum(int[] numbers, int i, List<List<Integer>> results) {
        int j = i + 1;
        int k = numbers.length - 1;

        while (j < k) {
            if (numbers[i] + numbers[j] + numbers[k] == 0) {
                List<Integer> result = new ArrayList<>();
                result.add(numbers[i]);
                result.add(numbers[j]);
                result.add(numbers[k]);
                result.add(99999);
                result.add(i);
                result.add(j);
                result.add(k);
                results.add(result);
                ++j;
            } else if (numbers[i] + numbers[j] + numbers[k] < 0) {
                ++j;
            } else {
                --k;
            }

        }
    }

}
