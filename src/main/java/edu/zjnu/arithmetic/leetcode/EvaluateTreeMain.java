package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-02-06 09:14:40
 * @description: EvaluateTreeMain
 */
public class EvaluateTreeMain {

    public boolean evaluateTree(TreeNode root) {


        if (root == null) {
            return false;
        }

        return compute(root);
    }

    private boolean compute(TreeNode node) {
        if (node.left != null && node.right != null) {
            boolean leftValue = compute(node.left);
            boolean rightValue = compute(node.right);

            if (node.val == 2) {
                return leftValue || rightValue;
            }

            if (node.val == 3) {
                return leftValue && rightValue;
            }

            return false;
        }

        return node.val == 1;
    }

    //
    // private static class TreeNode {
    //     int val;
    //     TreeNode left;
    //     TreeNode right;
    //
    //     TreeNode() {
    //     }
    //
    //     TreeNode(int val) {
    //         this.val = val;
    //     }
    //
    //     TreeNode(int val, TreeNode left, TreeNode right) {
    //         this.val = val;
    //         this.left = left;
    //         this.right = right;
    //     }
    // }


}
