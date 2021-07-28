package edu.zjnu.spring.aop.aopconfig;

/**
 * @description: Me关注于睡觉的逻辑，但是睡觉需要其他功能辅助，比如睡前脱
 * 衣服，起床脱衣服，这里开始就需要AOP替“Me”完成！解耦！首先需要一个SleepHelper类。因
 * 为一个是切入点前执行、一个是切入点之后执行，所以实现对应接口。
 * @author: 杨海波
 * @date: 2021-07-28
 **/
public class SleepHelper {

    public void beforeSleep() {
        System.out.println("睡觉前要脱衣服!");
    }

    public void afterSleep() {
        System.out.println("睡醒了要穿衣服！");
    }
}
