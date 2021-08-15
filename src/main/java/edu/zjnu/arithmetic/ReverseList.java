package edu.zjnu.arithmetic;

/**
 * @author 杨海波
 * @Description
 * @create 2021 05 05
 */
public class ReverseList {

    private static Node reverseByIterate(Node head) {
        Node pre = null, next;

        Node itera = head;

        while (itera != null) {
            next = itera.next;
            itera.next = pre;
            pre = itera;
            itera = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        Node node_4 = new Node(4, null);
        Node node_3 = new Node(3, node_4);
        Node node_2 = new Node(2, node_3);
        Node node_1 = new Node(1, node_2);
        Node node_0 = new Node(0, node_1);

        Node nodeReverse1 = reverseByIterate(node_0);

        Node nodeReverse2 = reverseByRecursion(node_0);

        System.out.println(nodeReverse1);

    }

    private static Node reverseByRecursion(Node node_0) {

        if (null == node_0 || null == node_0.next) {
            return node_0;
        }

        Node newNode = reverseByRecursion(node_0.next);

        return newNode;
    }

    public native int hashCode();

    private static class Node {
        int val;
        Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
