package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2023-07-12 10:45:23
 * @description: ReverseKGroup
 */
public class ReverseKGroup {

    public static void main(String[] args) {

    }

    // 遍历反转可实现原地反转
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1, head);
        // 计算长度
        int length = getListLength(head);
        // 需要反转的子链表个数
        int totalSubListNum = length / k + 1;

        //处理第 sub 个数组
        for (int sub = 0; sub < length / k + 1; sub++) {
            // 执行反转
            for (int index = 0; index < k; index++) {

            // 链表反转

            }
        }

        return dummy.next;
    }

    /**
     * 计算链表长度
     *
     * @param head
     * @return
     */
    private static int getListLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }

        return length;
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



