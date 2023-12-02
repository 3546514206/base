package edu.zjnu;

/**
 * @author: 杨海波
 * @date: 2023-12-01 14:17:18
 * @description: 节点数据结构
 */
public class Node {

    int value;
    Node[] next;

    public Node(int value, int level) {
        this.value = value;
        this.next = new Node[level + 1];
    }
}
