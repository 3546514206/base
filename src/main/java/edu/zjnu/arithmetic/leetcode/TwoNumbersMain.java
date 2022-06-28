package edu.zjnu.arithmetic.leetcode;

/**
 * @description: 两数之和
 * @author: 杨海波
 * @date: 2022-06-28 08:45
 **/
public class TwoNumbersMain {

    /**
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{7, 2, 11, 15};
        int target = 9;
        int[] result = twoNumbers(nums, target);
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoNumbers(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[]{-1, -1};

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }

        return result;
    }
}
