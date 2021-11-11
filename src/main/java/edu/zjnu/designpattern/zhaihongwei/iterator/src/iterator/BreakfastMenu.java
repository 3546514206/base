package edu.zjnu.designpattern.zhaihongwei.iterator.src.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/3/28
 */
public class BreakfastMenu {

    private List menu = new ArrayList<>();

    /**
     * 早餐店的菜单中已经有的
     */
    public void setMenu() {
        menu.add("皮蛋瘦肉粥");
        menu.add("肉蟹龙虾粥");
        menu.add("白粥");
        menu.add("豆浆");
    }

    public List getMenu() {
        return menu;
    }
}
