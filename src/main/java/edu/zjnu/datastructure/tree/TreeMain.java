package edu.zjnu.datastructure.tree;

/**
 * @description: 构造一棵树
 * @author: 杨海波
 * @date: 2021-09-14
 **/
public class TreeMain {

    public static void main(String[] args) {
        TreeNode<Integer> node_1 = new TreeNode<>(1);
        TreeNode<Integer> node_2 = new TreeNode<>(2);
        TreeNode<Integer> node_3 = new TreeNode<>(3);
        TreeNode<Integer> node_4 = new TreeNode<>(4);
        TreeNode<Integer> node_5 = new TreeNode<>(5);
        TreeNode<Integer> node_6 = new TreeNode<>(6);
        TreeNode<Integer> node_7 = new TreeNode<>(7);
        TreeNode<Integer> node_8 = new TreeNode<>(8);

        node_3.left = node_1;
        node_3.left.left = node_5;
        node_3.left.right = node_7;
        node_3.left.right.left = node_8;
        node_3.right = node_4;
        node_3.right.left = node_6;
        node_3.right.right = node_2;

        System.out.println(node_3);
    }
}
