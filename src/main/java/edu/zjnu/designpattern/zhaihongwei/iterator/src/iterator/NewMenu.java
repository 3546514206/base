package edu.zjnu.designpattern.zhaihongwei.iterator.src.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/3/28
 * 新的菜单类,相当于容器
 */
public class NewMenu {

    private List newMenu = new ArrayList();

    public List getNewMenu() {
        return newMenu;
    }

    /**
     * 给新的餐单添加菜品
     * 注意：不考虑数组和集合之间的操作方法，为了说明迭代器的作用
     *
     * @param breakfastMenu
     * @param lunchMenu
     */
    public void setNewMenu(List breakfastMenu, String[] lunchMenu) {

        for (Object item : breakfastMenu) {
            newMenu.add(item);
        }

        for (Object item : lunchMenu) {
            newMenu.add(item);
        }
    }

}
