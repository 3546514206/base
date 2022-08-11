package edu.zjnu.arithmetic.leetcode;

/**
 * @description: ConstructMaximumBinaryTreeMain
 * @author: 杨海波
 * @date: 2022-08-11 10:20
 **/
public class ConstructMaximumBinaryTreeMain {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        TreeNode root = new ConstructMaximumBinaryTreeMain().constructMaximumBinaryTree(nums);
        System.out.println(root);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    /**
     * 返回任意子树的根节点的索引，根节点是当前子树的最大节点
     */
    private TreeNode construct(int[] nums, int l, int r) {
        // 无后代节点
        if (l > r) {
            return null;
        }
        int maxIndex = maxIndex(nums, l, r);
        TreeNode root = new TreeNode();
        root.val = nums[maxIndex];
        // 递归
        root.left = construct(nums, l, maxIndex - 1);
        root.right = construct(nums, maxIndex + 1, r);

        return root;
    }

    private int maxIndex(int[] nums, int l, int r) {
        int maxIndex = l;
        // 寻找最大节点的下标
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
