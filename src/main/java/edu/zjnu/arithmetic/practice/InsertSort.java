package edu.zjnu.arithmetic.practice;

/**
 * @description: 插入排序
 * @author: 杨海波
 * @date: 2021-09-04
 **/
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{56, 23, 46, 72, 12, 24, 62, 30, 18, 62, 16, 35, 46, 36};

        int[] rs = test(arr);
        for (int i = 0; i < rs.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    private static int[] test(int[] arr) {
        // 处理特殊情况
        if (arr.length < 2) {
            return arr;
        }

        // 下标从1开始
        for (int i = 1; i < arr.length; i++) {
            // 保存待处理的数据
            int temp;

            int j = i;
            while (j > 0) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }

                j--;
            }

        }


        return arr;
    }
}
