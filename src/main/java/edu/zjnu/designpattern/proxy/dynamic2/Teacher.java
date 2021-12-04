package edu.zjnu.designpattern.proxy.dynamic2;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-12-04
 **/
public class Teacher implements People {

    @Override
    public String work() {
        System.out.println("老师教书育人...");
        return "教书";
    }

}
