package edu.zjnu.designpattern.zhaihongwei.strategy.src.strategy;

/**
 * Create by zhaihongwei on 2018/3/23
 * 交警类
 */
public class TrafficPolice {

    /**
     * 持有具体的策略
     */
    private StrategyToDrinkDriving strategy;

    /**
     * 交警类根据具体情况使用不同的策略
     *
     * @param strategy
     */
    public TrafficPolice(StrategyToDrinkDriving strategy) {
        this.strategy = strategy;
    }

    /**
     * 执行策略
     */
    public void doStrategy() {
        strategy.strategyInterface();
    }
}
