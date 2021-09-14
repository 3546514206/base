package edu.zjnu.arithmetic.practice;

/**
 * @description: BubbleSort
 * @author: 杨海波
 * @date: 2021-09-06
 **/
public class BubbleSort {


    public static void main(String[] args) {
        int[] arr = new int[]{56, 23, 46, 72, 12, 27, 62, 30, 18, 62, 16, 35, 46, 36};

        int[] rs = sort(arr);
        for (int i = 0; i < rs.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static int[] sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - (i + 1); j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }

        }

        return arr;
    }
}
