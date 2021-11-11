package edu.zjnu.designpattern.zhaihongwei.strategy.src.strategy;

/**
 * Create by zhaihongwei on 2018/3/23
 * 测试类
 */
public class StrategyTest {

    public static void main(String[] args) {

        System.out.println("同志配合一下,对着酒精检测仪吹一下！！！");
        System.out.println(":(");
        TrafficPolice trafficPolice = new TrafficPolice(new MoreDrinkDriving());
        trafficPolice.doStrategy();
    }
}
