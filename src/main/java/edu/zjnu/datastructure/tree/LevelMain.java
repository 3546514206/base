package edu.zjnu.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 用JDK的队列实现层次遍历
 * @author:
 * @date: 2021-09-21
 **/
public class LevelMain {

    public static void main(String[] args) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        TreeNode<Integer> root = TreeMain.buildTree();
        levelOder(root, queue);
    }

    private static void levelOder(TreeNode<Integer> root, Queue<TreeNode<Integer>> queue) {
        if (null == root) {
            return;
        }

        //根节点入队
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 队列第一个节点出队
            TreeNode<Integer> front = queue.poll();
            // 访问出队节点的数据域
            System.out.println(front.value);

            if (null != front.left) {
                //当前出队节点左孩子不为空则左孩子入队列
                queue.offer(front.left);
            }

            if (null != front.right) {
                //当前出队节点右孩子不为空则右孩子入队列
                queue.offer(front.right);
            }
        }
    }
}
