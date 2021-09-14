package edu.zjnu.base.base.java8interface;

/**
 * @description: 接口可以有默认方法和静态方法
 * @author: 杨海波
 * @date: 2021-09-01
 **/
public interface Person {

//    public void run(){
//
//   }
//
//    private void sing(){
//
//    }

    default void say() {
        System.out.println("i am a person");
    }

    static void learn() {
        System.out.println("i am a person,i am learning");
    }
}
