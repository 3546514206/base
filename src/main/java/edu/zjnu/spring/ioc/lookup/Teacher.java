package edu.zjnu.spring.ioc.lookup;

/**
 * @description: Teacher
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class Teacher extends User {

    @Override
    public void showMe() {
        System.out.println("i am a teacher");
    }
}
