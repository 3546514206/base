package edu.zjnu.designpattern.zhaihongwei.template;

/**
 * Create by zhaihongwei on 2018/3/24
 * 具体的模板
 * 顾客B
 */
public class CustomerA extends SpicyFragrantPot {

    @Override
    public void choose() {
        System.out.println("顾客A挑选了balabala好多菜！！！");
    }

    @Override
    public boolean isHotHook() {
        System.out.println("顾客A需要加辣！！！");
        return true;
    }

    @Override
    public void coleHook() {
        System.out.println("顾客A自己打了一碗汤，加了一点咸菜");
    }
}
