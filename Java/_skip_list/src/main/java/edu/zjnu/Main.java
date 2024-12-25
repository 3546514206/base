package edu.zjnu;

/**
 * @author: 杨海波
 * @date: 2023-12-01 14:16:45
 * @description: 主函数
 */
public class Main {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(9);
        skipList.insert(12);
        skipList.insert(19);
        skipList.insert(17);
        skipList.insert(26);
        skipList.insert(21);
        skipList.insert(25);

        skipList.printSkipList();

        System.out.println("Search 19: " + skipList.search(19)); // true
        System.out.println("Search 8: " + skipList.search(8));   // false

        skipList.delete(19);
        skipList.delete(21);

        skipList.printSkipList();
    }
}
