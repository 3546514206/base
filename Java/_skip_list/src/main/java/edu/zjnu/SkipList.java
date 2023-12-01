package edu.zjnu;

import java.util.Random;

/**
 * @author: 杨海波
 * @date: 2023-12-01 14:17:43
 * @description: 跳表数据结构
 */
public class SkipList {

    // 最大层数
    private static final int MAX_LEVEL = 16;

    // 当前层数
    private int level = 0;

    // 虚拟头节点
    private Node head = new Node(-1, MAX_LEVEL);
    private Random random = new Random();

    // 获取一个随机层数，用于新插入的节点
    private int randomLevel() {
        int level = 0;
        while (random.nextDouble() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    // 插入节点
    public void insert(int value) {
        int newLevel = randomLevel();
        Node newNode = new Node(value, newLevel);

        Node current = head;
        Node[] update = new Node[MAX_LEVEL + 1];

        // 从最高层开始找到每一层的插入位置
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }

        // 更新当前层数
        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            level = newLevel;
        }

        // 在每一层插入新节点
        for (int i = 0; i <= newLevel; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    // 搜索节点
    public boolean search(int value) {
        Node current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
        }
        return current.next[0] != null && current.next[0].value == value;
    }

    // 删除节点
    public void delete(int value) {
        Node current = head;
        Node[] update = new Node[MAX_LEVEL + 1];

        // 从最高层开始找到每一层的删除位置
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }

        // 找到要删除的节点
        if (current.next[0] != null && current.next[0].value == value) {
            // 从每一层删除节点
            for (int i = 0; i <= level; i++) {
                if (update[i].next[i] != null && update[i].next[i].value == value) {
                    update[i].next[i] = update[i].next[i].next[i];
                }
            }

            // 更新当前层数
            while (level > 0 && head.next[level] == null) {
                level--;
            }
        }
    }

    // 打印跳表
    public void printSkipList() {
        for (int i = level; i >= 0; i--) {
            Node current = head;
            System.out.print("Level " + i + ": ");
            while (current.next[i] != null) {
                System.out.print(current.next[i].value + " ");
                current = current.next[i];
            }
            System.out.println();
        }
    }
}
