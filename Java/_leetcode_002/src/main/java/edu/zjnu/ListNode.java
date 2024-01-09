package edu.zjnu;

/**
 * @author 杨海波
 * @date 2024/1/9 14:15
 * @description ListNode
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}