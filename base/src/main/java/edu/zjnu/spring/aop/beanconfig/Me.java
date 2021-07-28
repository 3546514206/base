package edu.zjnu.spring.aop.beanconfig;

/**
 * @description: 接口实现类，“Duck”可以睡觉，“Duck”就实现可以睡觉的接口。
 * @author: 杨海波
 * @date: 2021-07-28
 **/
public class Me implements Sleepable {
    public void sleep() {
        System.out.println("睡觉！不休息哪里有力气学习！");
    }
}
