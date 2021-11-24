package edu.zjnu.datastructure.tree;

/**
 * @description: 求二叉树的深度
 * @author: 杨海波
 * @date: 2021-09-21
 **/
public class DepthMain {

    private static int depth = 0;
    private static int d = 0;

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeMain.buildTree();
        System.out.println(getDepth(root));

        getDepthV2(root);
        System.out.println(depth);
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


    /**
     * 求深度：分治思想，递归实现
     * @param root
     */
    private static void getDepthV2(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        depth++;
        d++;
        comDepth(root);
    }

    private static void comDepth(TreeNode<Integer> root) {

        if (root.left != null) {
            d++;
            comDepth(root.left);
            d--;
        }

        if (root.right != null) {
            d++;
            comDepth(root.right);
            d--;
        }

        if (root.left == null && root.right == null) {
            depth = Math.max(d, depth);
        }

    }

}
