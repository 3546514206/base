package edu.zjnu.arithmetic.practice;

import edu.zjnu.base.LogInterFace;

/**
 * @author 杨海波
 * @description 写一下二分查找
 * @date 2021-02-19 16:36
 */
class BinarySearch implements LogInterFace {

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 8, 13, 16, 26, 48};
        BinarySearch binarySearch = new BinarySearch();
        log.info(binarySearch.binarySearch(a, 8));
    }

    /**
     * @param arr    数组
     * @param target 查找的数据
     * @return 下标
     */
    int binarySearch(int[] arr, int target) {
        int l_index = 0;
        int r_index = arr.length - 1;


        while (l_index <= r_index) {
            int m_index = l_index + (r_index - l_index) / 2;

            if (arr[m_index] < target) {

                l_index = m_index + 1;
            } else if (arr[m_index] == target) {

                return m_index;
            } else {

                r_index = m_index - 1;
            }
        }

        return -1;
    }

}
