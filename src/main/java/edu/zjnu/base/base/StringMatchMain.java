package edu.zjnu.base.base;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-08-22 14:17
 **/
public class StringMatchMain {


    public static void main(String[] args) {
        String reg = "12345678qwertyuasdfgh";
        String asd = "asd";
        System.out.println(reg.contains(asd.trim()));
    }
}
