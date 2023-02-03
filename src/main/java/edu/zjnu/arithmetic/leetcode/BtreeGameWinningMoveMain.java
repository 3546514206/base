package edu.zjnu.arithmetic.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: 杨海波
 * @date: 2023-02-03 09:21:38
 * @description: todo
 */
public class BtreeGameWinningMoveMain {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        System.out.println(new BtreeGameWinningMoveMain().btreeGameWinningMove(n1, 5, 1));
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {

        // 层次遍历找出 x 节点
        TreeNode xNode = locateXNode(root, x);
        if (xNode == null) {
            return false;
        }

        // x 节点左子树的总个数
        int leftTreeSum = computeSubTreeSum(xNode.left);
        // x 节点右子树的总个数
        int rightTreeSun = computeSubTreeSum(xNode.right);

        // 先手将整棵树划分为三个区域，如果存在某个区域节点个数大于 n/2 ,则后手能赢
        return isGreatThanHalfN(leftTreeSum, rightTreeSun, n);
    }

    private boolean isGreatThanHalfN(int leftTreeSum, int rightTreeSun, int n) {
        // 左子树区域，右子树区域，父节点区域任意一个区域大于 N/2 ,后手均能赢
        int half = n / 2 + 1;
        return leftTreeSum >= half || rightTreeSun >= half || (n - leftTreeSum - rightTreeSun - 1) >= half;
    }

    // 计算二叉树节点个数
    private int computeSubTreeSum(TreeNode root) {
        // 堆内存分配变量，方便递归中修改
        IntegerHolder count = new IntegerHolder();

        if (root == null) {
            return count.getValue();
        }
        // 根节点非空，计数
        count.increase();
        // 中序遍历统计二叉树节点个数
        midOderSubTree(root.left, count);
        midOderSubTree(root.right, count);
        return count.getValue();
    }

    private void midOderSubTree(TreeNode root, IntegerHolder count) {
        if (root != null) {
            count.increase();
            midOderSubTree(root.left, count);
            midOderSubTree(root.right, count);
        }
    }

    private TreeNode locateXNode(TreeNode root, int x) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 队列头出队列
            TreeNode head = queue.poll();
            // 如果是 x 节点则记录下来，并退出循环
            if (head.val == x) {
                return head;
            }

            // 层次遍历其余代码，子节点入队列
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }

        return null;
    }

    // 整形变量包括包装类型不可变，IntegerHolder用于解决引用传值问题
    private static class IntegerHolder {
        private int val = 0;

        void increase() {
            val++;
        }

        public int getValue() {
            return this.val;
        }
    }
}
