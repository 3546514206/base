package edu.zjnu.base.base.ref;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-20 16:28
 **/
public class ChangeString {

    public static void changeStr(String str) {
        System.out.println(str);
        str = "welcome";
    }

    public static void main(String[] args) {

        String str = "1234";
        changeStr(str);
        System.out.println(str);
    }
}
