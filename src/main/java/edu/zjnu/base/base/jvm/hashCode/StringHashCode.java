package edu.zjnu.base.base.jvm.hashCode;

/**
 * @author: 杨海波
 * @date: 2022-12-30 17:28:06
 * @description: todo
 */
public class StringHashCode {

    public static void main(String[] args) {
        String string = "11111";
        System.out.println(string.hashCode());
        string = string + "222";
        System.out.println(string.hashCode());
    }
}
