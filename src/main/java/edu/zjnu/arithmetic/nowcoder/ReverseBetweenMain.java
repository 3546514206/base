package edu.zjnu.arithmetic.nowcoder;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-15 11:12
 **/
public class ReverseBetweenMain {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        ListNode pre = null;
        ListNode next = null;
        ListNode rz = null;
        if (m > 1) {
            rz = head;
        }
        for (int index = 1; index <= n; index++) {
            if (index + 1 >= m) {
                pre = head;
            }
            if (index >= m && index <= n) {


            }

            head = head.next;
        }

        return rz;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}




