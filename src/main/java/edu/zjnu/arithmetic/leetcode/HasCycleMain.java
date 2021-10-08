package edu.zjnu.arithmetic.leetcode;

/**
 * @description: 力扣第141题
 * @author: 杨海波
 * @date: 2021-10-08
 **/
public class HasCycleMain {

    public static void main(String[] args) {
        HasCycleMainListNode node3 = new HasCycleMainListNode(3);
        HasCycleMainListNode node2 = new HasCycleMainListNode(2);
        HasCycleMainListNode node0 = new HasCycleMainListNode(0);
        HasCycleMainListNode node_4 = new HasCycleMainListNode(-4);

        node3.next = node2;
        node2.next = node0;
        node0.next = node_4;
        node_4.next = node2;

        System.out.println(hasCycle(node3));

    }

    /**
     * 快慢指针：如果有环必然会相遇，无环则必然会结束循环。相遇则跳出。
     * @param head
     * @return
     */
    public static boolean hasCycle(HasCycleMainListNode head) {

        // 快慢指针初始指向首节点(提交力扣时HasCycleMainListNode换为ListNode)
        HasCycleMainListNode fast = head;
        HasCycleMainListNode slow = head;

        //PS如果 next.next 是null也成不了换
        // 条件短路
        while (slow != null && fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;
        }

        return false;
    }
}


class HasCycleMainListNode {
    int val;

    HasCycleMainListNode next;

    HasCycleMainListNode(int x) {
        val = x;
        next = null;
    }
}
