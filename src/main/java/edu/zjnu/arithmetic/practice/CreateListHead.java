package edu.zjnu.arithmetic.practice;

/**
 * @author: 杨海波
 * @date: 2022-11-05 15:18:44
 * @description: 头插法创建链表，是倒着的
 */
public class CreateListHead {

    public static void main(String[] args) {

        String[] toCreateList = new String[]{"11", "22", "66", "44", "88", "55"};
        CreateListHead_Node head = new CreateListHead_Node();

        // 顺序为：  55 -> 88 -> 44 -> 66 -> 22 -> 11
        for (int i = 0; i < toCreateList.length; i++) {

            CreateListHead_Node insert = new CreateListHead_Node();
            insert.next = head.next;
            head.next = insert;

            insert.data = toCreateList[i];
        }

        System.out.println(head);
    }

    static class CreateListHead_Node {
        String data;
        CreateListHead_Node next;
    }

}
