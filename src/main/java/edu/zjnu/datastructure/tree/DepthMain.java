package edu.zjnu.datastructure.tree;

/**
 * @description: 求二叉树的深度
 * @author: 杨海波
 * @date: 2021-09-21
 **/
public class DepthMain {

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeMain.buildTree();
        System.out.println(getDepth(root));

    }

    /**
     * 求深度：分治思想，递归实现
     * @param root
     */
    private static int getDepth(TreeNode<Integer> root) {

        if (null == root) return 0;

        int ld = getDepth(root.left);
        int rd = getDepth(root.right);

        return Math.max(ld, rd) + 1;
    }
}
