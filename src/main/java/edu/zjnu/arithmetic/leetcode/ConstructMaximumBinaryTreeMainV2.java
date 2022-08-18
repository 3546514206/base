package edu.zjnu.arithmetic.leetcode;

import java.util.Stack;

/**
 * @description: ConstructMaximumBinaryTreeMainV2 基于单调栈构建最大树(没写完，不会)
 * @author: 杨海波
 * @date: 2022-08-12 10:29
 **/
public class ConstructMaximumBinaryTreeMainV2 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        ConstructMaximumBinaryTreeMainV2.TreeNode root = new ConstructMaximumBinaryTreeMainV2().constructMaximumBinaryTree(nums);
        System.out.println(root);
    }

    private ConstructMaximumBinaryTreeMainV2.TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<Integer> stack = new Stack<>();


        return null;
    }

    private class TreeNode {
        int val;
        ConstructMaximumBinaryTreeMainV2.TreeNode left;
        ConstructMaximumBinaryTreeMainV2.TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, ConstructMaximumBinaryTreeMainV2.TreeNode left, ConstructMaximumBinaryTreeMainV2.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

