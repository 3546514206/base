package edu.zjnu.spring.aop.beanconfig;

import edu.zjnu.spring.aop.Sleepable;

/**
 * @description: 接口实现类，“Me”可以睡觉，“Me”就实现可以睡觉的接口。
 * @author: 杨海波
 * @date: 2021-07-28
 **/
public class Me implements Sleepable {
    public void sleep() {
        System.out.println("睡觉！不休息哪里有力气学习！");
    }
}
