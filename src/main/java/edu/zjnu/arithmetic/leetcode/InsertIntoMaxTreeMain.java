package edu.zjnu.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-15 10:06
 **/
public class InsertIntoMaxTreeMain {

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node4.left = node1;
        node4.right = node3;
        node3.left = node2;

        TreeNode newTreeRoot = new InsertIntoMaxTreeMain().insertIntoMaxTree(node4, 5);
        System.out.println(newTreeRoot.val);
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        List<Integer> list = midOderTraverse(root);
        list.add(val);
        TreeNode newTreeRoot = new TreeNode();

        return buildMaxTree(list, newTreeRoot, 0, list.size() - 1);
    }

    /**
     * 构建最大树
     *
     * @param list        4 1 3 2 5
     * @param newTreeRoot
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    private TreeNode buildMaxTree(List<Integer> list, TreeNode newTreeRoot, int leftIndex, int rightIndex) {

        if (leftIndex <= rightIndex) {
            int maxIndex = leftIndex;
            int maxValue = list.get(maxIndex);
            for (int i = leftIndex; i <= rightIndex; i++) {
                if (list.get(i) > maxValue) {
                    maxIndex = i;
                    maxValue = list.get(i);
                }
            }

            newTreeRoot.val = maxValue;

            newTreeRoot.left = buildMaxTree(list, new TreeNode(), leftIndex, maxIndex - 1);
            newTreeRoot.right = buildMaxTree(list, new TreeNode(), maxIndex + 1, rightIndex);
            return newTreeRoot;
        }

        return null;
    }

    List<Integer> midOderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursionTraverse(list, root);
        return list;
    }

    /**
     * 中序递归遍历
     *
     * @param list
     * @param root
     */
    private void recursionTraverse(List<Integer> list, TreeNode root) {
        // 中序
        list.add(root.val);
        // 递归
        if (root.left != null) {
            recursionTraverse(list, root.left);
        }
        if (root.right != null) {
            recursionTraverse(list, root.right);
        }
    }

   static class TreeNode {
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


