package edu.zjnu.spring.aop.aopconfig;

/**
 * @description: 接口实现类，“Duck”可以睡觉，“Duck”就实现可以睡觉的接口。
 * @author: 杨海波
 * @date: 2021-07-28
 **/
public class Duck implements Swimable {
    public void swim() {
        System.out.println("我在游泳！");
    }
}
