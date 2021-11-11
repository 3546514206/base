package edu.zjnu.designpattern.zhaihongwei.iterator.src.iterator;

import java.util.Iterator;

/**
 * Create by zhaihongwei on 2018/3/28
 */
public class LunchMenu implements Iterator {

    private String[] menu = new String[5];
    private int index;

    /**
     * 午餐店已经有的菜单
     */
    public void setMenu() {
        menu[0] = "西红柿炒蛋";
        menu[1] = "水蒸蛋";
        menu[2] = "油泼面";
        menu[3] = "白米饭";
    }

    public String[] getMenu() {
        return menu;
    }

    @Override
    public boolean hasNext() {
        // 没有下一个元素了
        return index < menu.length - 1 && menu[index] != null;
    }

    @Override
    public Object next() {
        Object o = null;
        if (hasNext()) {
            o = menu[index];
            index++;
        }
        return o;
    }
}
