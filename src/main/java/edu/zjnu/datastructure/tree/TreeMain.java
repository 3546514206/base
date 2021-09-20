package edu.zjnu.datastructure.tree;

import com.sun.istack.internal.NotNull;

import java.util.Scanner;

/**
 * @description: 构造一棵树，二叉树的先后中遍历
 * @author: 杨海波
 * @date: 2021-09-14
 **/
public class TreeMain {

    /**
     * 队列大小
     */
    private static final int MAX_NODES = 100;

    public static void main(String[] args) {
        TreeNode<Integer> root = buildTree();

        Scanner scanner = new Scanner(System.in);
        Boolean ctn = Boolean.TRUE;
        while (ctn) {
            System.out.println("遍历方式（整数）：1-先序，2-中序，3-后序，4-层次遍历");
            Integer type = scanner.nextInt();
            oder(root, OderType.getInstance(type));
            System.out.println("是否继续？Y/N");
            String c = scanner.next();
            if (!"Y".equals(c) && !"y".equals(c)) {
                ctn = Boolean.FALSE;
                System.out.println("谢谢使用");
            }
        }


    }

    private static void oder(TreeNode<Integer> root, @NotNull OderType type) {
        switch (type) {
            case MID: {
                System.out.print("中序遍历：");
                midOder(root);
                break;
            }
            case PRE: {
                System.out.print("先序遍历：");
                preOder(root);
                break;
            }
            case AFTER: {
                System.out.print("后续遍历：");
                afterOder(root);
                break;
            }
            case LEVEL: {
                System.out.print("层次遍历：");
                levelOrder(root);
                break;
            }
            default: {
                System.out.print("error type!");
            }
        }
    }

    /**
     *
     * @param root
     */
    private static void levelOrder(TreeNode<Integer> root) {
        // 构造一个队列
        TreeNode<Integer>[] queue = new TreeNode[MAX_NODES];
        // 队头指针和队尾指针，队头初始为-1，队尾初始为0，表示初始状态队列没有数据
        int front = -1, rear = 0;

        if (null == root) {
            return;
        }

        // 根节点进队列
        queue[rear] = root;

        while (front != rear) {
            front++;
            // 访问数据域
            System.out.print(queue[front].value + " ");
            //队列首节点左孩子进队列
            if (null != queue[front].left) {
                rear++;
                queue[rear] = queue[front].left;
            }
            //将队首节点的右孩子节点进队列
            if (null != queue[front].right) {
                rear++;
                queue[rear] = queue[front].right;
            }
        }

    }

    /**
     * 后序遍历
     * @param node
     */
    private static void afterOder(TreeNode<Integer> node) {
        if (null != node) {
            afterOder(node.left);
            afterOder(node.right);
            System.out.print(node.value + " ");
        }
    }


    /**
     * 中序遍历
     * @param node
     */
    private static void midOder(TreeNode<Integer> node) {
        if (null != node) {
            midOder(node.left);
            System.out.print(node.value + " ");
            midOder(node.right);
        }
    }

    /**
     *  先序遍历
     * @param node
     */
    private static void preOder(TreeNode<Integer> node) {

        if (null != node) {
            System.out.print(node.value + " ");
            preOder(node.left);
            preOder(node.right);
        }
    }

    private static TreeNode<Integer> buildTree() {
        TreeNode<Integer> node_1 = new TreeNode<>(1);
        TreeNode<Integer> node_2 = new TreeNode<>(2);
        TreeNode<Integer> node_3 = new TreeNode<>(3);
        TreeNode<Integer> node_4 = new TreeNode<>(4);
        TreeNode<Integer> node_5 = new TreeNode<>(5);
        TreeNode<Integer> node_6 = new TreeNode<>(6);
        TreeNode<Integer> node_7 = new TreeNode<>(7);
        TreeNode<Integer> node_8 = new TreeNode<>(8);

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

    public enum OderType {
        /**
         * 怎么理解枚举？如果把枚举的定义改成成类的定义方式：
         * public class OderType
         * 枚举就相当于可以公开访问的静态常量
         * public static final PRE = new OderType(1);
         * public static final MID = new OderType(2);
         * public static final AFTER = new OderType(3);
         * public static final LEVEL = new OderType(4);
         * 特别指出：枚举的构造方法默认是私有的，而且自带了values()方法获取所有的"静态常量"（枚举）
         */
        PRE(1), MID(2), AFTER(3), LEVEL(4);

        private Integer value;


        // 枚举的构造方法都是private
        OderType(Integer value) {
            this.value = value;
        }

        public static OderType getInstance(Integer value) {
            for (OderType oderType : values()) {
                if (oderType.getValue().equals(value)) {
                    return oderType;
                }
            }
            return null;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
