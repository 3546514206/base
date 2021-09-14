package edu.zjnu.arithmetic.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨海波
 * @Description 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）
 * 上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0],
 * nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在
 * 下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则
 * 返回它的下标，否则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * @create 2021-05-12
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(new RotateArray().search2(a, 3));
    }

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) {
                return m;
            }
            // 如果中间值大于右边，那么右边值是有序的
            else if (nums[m] < nums[r]) {

                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else {

                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }

        Map map = new HashMap();

        return -1;
    }

    /**
     * 开头岔开说一句，如果数组旋转两次，该算法就失灵了
     * 一次旋转的数组，其实是两个升序的数组
     * m会将整个数组分成一个升序和一个先升后降再升的两个数组，特殊情况是分成两个升序数组
     * 核心问题在于选分支，在先升后降的数组去判断是否是目标分支是做不到的，但是可以在升序数组很好判断
     * 如果判断在升序数组，就选升序数组，如果不在升序数组，就选另外的那个非升序数组
     * 这样做，特殊的也是能够支持的
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            //寻找二分点
            int m = l + (r - l) / 2;

            //返回target下标
            if (nums[m] == target) {
                return m;
            }
            //如果[m]大于[l],说明左边是升序数组
            if (nums[m] > nums[l]) {
                //在这种情况下，如果target>=[l]并且target<=[m]，就选左分支，否则选右分支
                if (target > nums[l] && target <= nums[m]) {
                    //选择左分支
                    r = m - 1;
                } else {
                    //选择右分支
                    l = m + 1;
                }
            }
            //如果[m]小于[r]，说明右边是升序数组
            if (nums[m] < nums[r]) {
                //在这种情况下，如果target>=[m]并且target<=[r]，就选右分支，否则选左分支
                if (target >= nums[m] && target < nums[r]) {
                    //选择右分支
                    l = m + 1;
                } else {
                    //选择左分支
                    r = m - 1;
                }
            }


        }

        return -1;
    }
}

