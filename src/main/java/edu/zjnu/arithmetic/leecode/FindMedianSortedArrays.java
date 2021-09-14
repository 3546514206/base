package edu.zjnu.arithmetic.leecode;

/**
 * @author 杨海波
 * @description 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * <p>
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * <p>
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 */
public class FindMedianSortedArrays {

    /**
     * 给定数组1和数组2，求归并排序之后的结果的中位数，中位数计算来源的下标是预先可知的
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //归并数组的长度
        int resultNumsSize = nums1.length + nums2.length;

        if (resultNumsSize == 0) {
            return 0D;
        }

        //求中位数的下标
        int index;

        if (resultNumsSize % 2 == 1) {
            index = (resultNumsSize - 1) / 2;

            int[] sortedNums = sort(nums1, nums2);

            return sortedNums[index];

        } else {
            index = (resultNumsSize - 2) / 2;

            int[] sortedNums = sort(nums1, nums2);

            return ((double) sortedNums[index] + (double) sortedNums[index + 1]) / 2;
        }
    }

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] sort(int[] nums1, int[] nums2) {
        int[] sorted = new int[nums1.length + nums2.length];

        //变量用于存储两个集合应该被比较的索引（存入新集合就+1）
        int a = 0;
        int b = 0;

        for (int i = 0; i < sorted.length; i++) {
            //两数组都未遍历完，相互比较后加入
            if (a < nums1.length && b < nums2.length) {
                if (nums1[a] > nums2[b]) {
                    sorted[i] = nums2[b];
                    b++;
                } else {
                    sorted[i] = nums1[a];
                    a++;
                }
            }

            //num2已经遍历完，无需比较，直接将剩余num1加入
            else if (a < nums1.length) {
                sorted[i] = nums1[a];
                a++;
            }

            //num1已经遍历完，无需比较，直接将剩余num2加入
            else if (b < nums2.length) {
                sorted[i] = nums2[b];
                b++;
            }
        }

        return sorted;
    }
}
