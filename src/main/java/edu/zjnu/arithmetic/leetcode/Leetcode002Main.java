package edu.zjnu.arithmetic.leetcode;

/**
 * @author: 杨海波
 * @date: 2022-12-01 10:52:29
 * @description: todo
 */
public class Leetcode002Main {

    public static void main(String[] args) {
//        243     564
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4, three);
        ListNode two = new ListNode(2, four);

        ListNode four_ = new ListNode(4);
        ListNode six = new ListNode(6, four_);
        ListNode five = new ListNode(5, six);

        ListNode rz = new Leetcode002Main().addTwoNumbers(two,five);
        System.out.println("zr");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 == null ? 0 : l1.val);
            int y = (l2 == null ? 0 : l2.val);

            int curVal = (x + y + carry) % 10;
            carry = (x + y + carry) / 10;

            ListNode node = new ListNode();
            node.val = curVal;
            cur.next = node;
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if(carry != 0){
            ListNode tail = new ListNode(carry);
            cur.next = tail;
            cur = cur.next;
        }
        return head.next;
    }


    private static class ListNode {
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
}
