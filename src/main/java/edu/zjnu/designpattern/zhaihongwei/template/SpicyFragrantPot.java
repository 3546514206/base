package edu.zjnu.designpattern.zhaihongwei.template;

/**
 * Create by zhaihongwei on 2018/3/24
 * 抽象模板
 * 抽象麻辣香锅类
 */
public abstract class SpicyFragrantPot {

    /**
     * 麻辣香锅制作的方法
     * 模板方法,声明为final，防止子类更改模板方法
     */
    public final void makingMethod() {
        // 选食材
        choose();
        // 根据客户需求判断是否执行加辣操作
        if (isHotHook()) {
            addHot();
        }
        // 制作并装盘
        makeAndDish();
        // 根据顾客喜好添加小菜，可以不加。
        coleHook();
    }

    /**
     * 挑选食材的方法
     * 抽象方法，由子类实现
     */
    public abstract void choose();

    /**
     * 询问顾客是否加辣，模式加辣，不然怎么叫麻辣香锅呢
     * hook方法的第一种作用，可以灵活的改变模板方法。
     */
    public boolean isHotHook() {
        return true;
    }

    /**
     * 加辣的方法
     * 具体方法，加辣的动作基本相同，由父类自己实现
     */
    public void addHot() {
        System.out.println("加了辣椒！！！");
    }

    /**
     * 厨师制作并装盘方法
     * 具体方法，装盘的动作基本是相同的，由父类自己实现。
     */
    public void makeAndDish() {
        System.out.println("装好盘啦！");
    }

    /**
     * 根据顾客喜好，自己加小菜，也可以不加
     * hook方法的第二种作用。
     */
    public void coleHook() {
        // 空实现，根据顾客，喜好自己实现
    }
}
