package edu.zjnu.arithmetic.leetcode;

import java.util.Arrays;

/**
 * @author: 杨海波
 * @date: 2023-06-27 11:39:46
 * @description: Rotate
 */
public class Rotate {


    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        new Rotate().rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }

        // 轴线边界值
        int axis = matrix.length / 2;
        // 从外向内
        // [index,index]                 [index,index + length - 1]
        // [length - 1 - index,index]    [length - 1 - index, length - 1 - index]
        // 是每一圈层的四个顶点
        int index = 0;
        while (index < axis) {
            // 顺时针交换四条边的每个节点
            // 游标，用于标记当前的处理节点的下标
            int vernier = 0;
            // 第 index 个圈层的的边长
            int len = matrix.length - 1 - (index + 1);
            // 顺时针挪动，边界为 vernier 小于有顶点的纵坐标的时候
            while (vernier <= len) {


                vernier++;
            }
            index++;
        }
    }
}
