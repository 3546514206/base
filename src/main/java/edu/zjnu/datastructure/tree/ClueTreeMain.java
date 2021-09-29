package edu.zjnu.datastructure.tree;

/**
 * @description: ClueTreeMain
 * @author: 杨海波
 * @date: 2021-09-28
 **/
public class ClueTreeMain {

    public static void main(String[] args) {
        ClueTreeNode root = buildTree();
        midClue(root);

    }

    /**
     * 中序线索化
     * @param root
     */
    private static void midClue(ClueTreeNode root) {
        if (null != root) {

        }
    }

    /**
     * 构造一课树（待线索化）
     * @return
     */
    private static ClueTreeNode buildTree() {
        ClueTreeNode<Integer> node_1 = new ClueTreeNode<>(1);
        ClueTreeNode<Integer> node_2 = new ClueTreeNode<>(2);
        ClueTreeNode<Integer> node_3 = new ClueTreeNode<>(3);
        ClueTreeNode<Integer> node_4 = new ClueTreeNode<>(4);
        ClueTreeNode<Integer> node_5 = new ClueTreeNode<>(5);
        ClueTreeNode<Integer> node_6 = new ClueTreeNode<>(6);
        ClueTreeNode<Integer> node_7 = new ClueTreeNode<>(7);
        ClueTreeNode<Integer> node_8 = new ClueTreeNode<>(8);

        /*
         *                 3
         *                /\
         *               1  4
         *              /\  /\
         *             5 7 6 2
         *              /
         *             8
         * */
        node_3.left = node_1;
        node_3.left.left = node_5;
        node_3.left.right = node_7;
        node_3.left.right.left = node_8;
        node_3.right = node_4;
        node_3.right.left = node_6;
        node_3.right.right = node_2;

        System.out.println(node_3);

        return node_3;
    }
}
