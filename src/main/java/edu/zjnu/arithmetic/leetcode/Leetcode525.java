package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2022-11-15 15:02:00
 * @description: todo
 */
public class Leetcode525 {

    public int findMaxLength(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        // 构造新数组         
        int[] newNums = new int[nums.length];
        // 前缀和数组         
        int[] sum = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                newNums[i] = -1;
            } else {
                newNums[i] = nums[i];
            }
        }

        sum[0] = newNums[0];

        for (int i = 1; i < newNums.length; i++) {
            sum[i] = sum[i - 1] + newNums[i];
        }

        int rz = 0;

        for (int i = newNums.length - 1; i >= 0; i--) {
            if(sum[i] == 0){
                rz = i;
                break;
            }
        }
        
        return rz;
    }
}
