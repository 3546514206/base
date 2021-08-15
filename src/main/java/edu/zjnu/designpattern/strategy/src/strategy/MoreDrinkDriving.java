package edu.zjnu.designpattern.strategy.src.strategy;

/**
 * Create by zhaihongwei on 2018/3/23
 * 醉驾策略
 */
public class MoreDrinkDriving implements StrategyToDrinkDriving {

    @Override
    public void strategyInterface() {
        System.out.println("血液中的酒精含量大于或者等于80mg/100ml,属于醉驾！");
        System.out.println("你是完蛋了，去警局待着吧你！！！吊销驾照，5年内不得重新获取驾照，经过判决后处以拘役，并处罚金");
    }
}
