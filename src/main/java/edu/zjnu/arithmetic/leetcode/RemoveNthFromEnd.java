package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-07-11 16:04:05
 * @description: 力扣第 19 题目
 */


class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟的头结点
        ListNode dummy = new ListNode(-1, head);
        // 计算长度
        int length = getLength(head);

        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1 ; i++) {
            cur = cur.next;
        }

        cur.next = cur.next.next;
        return dummy.next;
    }

    /**
     * 获取链表的长度
     *
     * @param head
     * @return
     */
    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    public static void main(String[] args) {
        // [1,2,3,4,5], n = 2
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode head = node1;

        new RemoveNthFromEnd().removeNthFromEnd(head, 2);

    }

    static class ListNode {

        int val;

        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
