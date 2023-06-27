package edu.zjnu.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: 杨海波
 * @date: 2023-06-26 15:04:52
 * @description: MergeMain
 */
public class MergeMain {

    public static void main(String[] args) {
        case01();
        case02();
        case03();
    }

    private static void case03() {
        int[][] x = {{1, 4}, {0, 4}};
        int[][] rz = new MergeMain().merge(x);
        System.out.println(Arrays.deepToString(rz));
    }

    private static void case02() {
        int[][] x = {{1, 3}};
        int[][] rz = new MergeMain().merge(x);
        System.out.println(Arrays.deepToString(rz));
    }

    private static void case01() {
        // 第一个[] 是最外面的的数组长度，数值是2，第二个[] 是内部数组的长度，数值是3
        int[][] x = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] rz = new MergeMain().merge(x);
        System.out.println(Arrays.deepToString(rz));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> rz = new ArrayList<>();
        int[] currentInterval = intervals[0];
        for (int index = 1; index < intervals.length; index++) {
            // interval 是 current 的下一个区间，也就是 current + 1
            int[] interval = intervals[index];

            // 如果 current + 1 数组的区间下限 小于等于 current 的区间上限
            if (interval[0] <= currentInterval[1]) {
                // 合并区间，用 current 保存合并之后的区间
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                // 不发生重叠，添加新区间至结果集
                rz.add(currentInterval);
                // 并将 current 指向下一个不连续的数组
                currentInterval = interval;
            }
        }

        // 不管有没有发生合并，都需要将最后一个加入结果集
        rz.add(currentInterval);

        return rz.toArray(new int[rz.size()][]);
    }
}
