package edu.zjnu.arithmetic.nowcoder;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-08 19:40
 **/
public class ReverseListMain {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode rz = new ReverseListMain().ReverseList(one);
        System.out.println(rz.val);
    }

    public ListNode ReverseList(ListNode head) {
       ListNode pre = null;
       ListNode next = null;
       while (head != null){
           next = head.next;
           head.next = pre;
           pre = head;
           head = next;
       }

       return pre;
    }

    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}

