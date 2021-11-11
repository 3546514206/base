package edu.zjnu.designpattern.zhaihongwei.strategy.src.strategy;

/**
 * Create by zhaihongwei on 2018/3/23
 * 饮酒驾驶策略
 */
public class DrinkDriving implements StrategyToDrinkDriving {

    @Override
    public void strategyInterface() {
        System.out.println("血液中的酒精含量大于或者等于20mg/100ml，小于80mg/100ml,属于饮酒驾驶！");
        System.out.println("去警局吧！罚款1000元—2000元、记12分并暂扣驾照6个月");
    }
}
