package edu.zjnu.designpattern.zhaihongwei.template;

/**
 * Create by zhaihongwei on 2018/3/24
 * 具体的模板
 * 顾客B
 */
public class CustomerB extends SpicyFragrantPot {

    @Override
    public void choose() {
        System.out.println("顾客B挑选了balabala好多菜！！！");
    }

    @Override
    public boolean isHotHook() {
        System.out.println("顾客B不需要加辣！！！");
        return false;
    }

    @Override
    public void coleHook() {
        System.out.println("顾客B只打了一碗汤！！！");
    }
}
