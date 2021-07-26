package edu.zjnu.designpattern.strategy.src.strategy;

/**
 * Create by zhaihongwei on 2018/3/23
 * 没有饮酒策略
 */
public class NoDrinkDriving implements StrategyToDrinkDriving {

    @Override
    public void strategyInterface() {
        System.out.println("没有喝酒，放行！");
        System.out.println("记住饮酒不开车，开车不饮酒！");
    }
}
