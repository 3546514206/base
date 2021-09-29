package edu.zjnu.datastructure.tree;

/**
 * @description: 线索二叉树的节点定义
 * @author: 杨海波
 * @date: 2021-09-28
 **/
public class ClueTreeNode<T> {

    public T value;

    public ClueTreeNode<T> left;

    public ClueTreeNode<T> right;

    // 0-数据节点
    public Boolean ltag;

    // 1-线索节点
    public Boolean rtag;

    public ClueTreeNode(T value) {
        this.value = value;
    }

}
