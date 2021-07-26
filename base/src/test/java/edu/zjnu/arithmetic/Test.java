package edu.zjnu.arithmetic;

import com.alibaba.fastjson.JSONObject;
import edu.zjnu.base.LogInterFace;
import org.junit.Assert;

/**
 * @author 杨海波
 * @description 力扣测试类
 * @date 2021-02-15 14:20
 */
public class Test implements LogInterFace {

    /**
     * 有人相爱，有人夜里开车看海，有人力扣第一题都做不出来
     */
    @org.junit.Test
    public void testTwoSum() {
        int[] nums = {3, 3};
        int target = 6;

        TwoSum twoSum = new TwoSum();

        //暴力方法
        int[] violence = twoSum.violence(nums, target);
        Assert.assertEquals(nums[violence[0]] + nums[violence[1]], target);

        //两遍哈希遍历
        int[] hashMapTwoTimes = twoSum.hashMapTwoTimes(nums, target);
        Assert.assertEquals(nums[hashMapTwoTimes[0]] + nums[hashMapTwoTimes[1]], target);
    }

    /**
     * 力扣第二题
     */
    @org.junit.Test
    public void testAddTwoNumbers() {
        ListNode l1_2 = new ListNode(3);
        ListNode l1_1 = new ListNode(4, l1_2);
        ListNode l1 = new ListNode(2, l1_1);

        ListNode l2_2 = new ListNode(4);
        ListNode l2_1 = new ListNode(6, l2_2);
        ListNode l2 = new ListNode(5, l2_1);


        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode listNode = addTwoNumbers.addTwoNumbers(l1, l2);

        log.info(JSONObject.toJSON(listNode).toString());
    }

    /**
     * 力扣第三题
     */
    @org.junit.Test
    public void testLengthOfLongestSubstring() {

        String s = " ";

        LongestSubstring longestSubstring = new LongestSubstring();

        int size = longestSubstring.lengthOfLongestSubstringInViolence(s);

        //int size = longestSubstring.longestSubstring(s);
        //asdasd
        Assert.assertEquals(size, 2);

    }

    /**
     * 力扣第四题
     */
    @org.junit.Test
    public void testFindMedianSortedArrays() {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};

        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double result = findMedianSortedArrays.findMedianSortedArrays(nums1, nums2);

        log.info(result);
    }
}
