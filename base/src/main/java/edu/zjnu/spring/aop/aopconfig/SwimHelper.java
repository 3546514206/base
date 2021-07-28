package edu.zjnu.spring.aop.aopconfig;

/**
 * @description: Me关注于睡觉的逻辑，但是睡觉需要其他功能辅助，比如睡前脱
 * 衣服，起床脱衣服，这里开始就需要AOP替“Duck”完成！解耦！首先需要一个SleepHelper类。因
 * 为一个是切入点前执行、一个是切入点之后执行，所以实现对应接口。
 * @author: 杨海波
 * @date: 2021-07-28
 **/
public class SwimHelper {

    public void beforeSwim() {
        System.out.println("游泳前要去拿救生圈!");
    }

    public void afterSwim() {
        System.out.println("游完了要去归还救生圈！");
    }
}
