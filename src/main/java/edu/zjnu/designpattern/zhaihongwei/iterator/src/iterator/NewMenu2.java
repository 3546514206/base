package edu.zjnu.designpattern.zhaihongwei.iterator.src.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/3/28
 * 新的菜单类,相当于容器
 */
public class NewMenu2 {

    private List newMenu = new ArrayList();
    private BreakfastMenu breakfastMenu;
    private LunchMenu lunchMenu;

    public NewMenu2(BreakfastMenu breakfastMenu, LunchMenu lunchMenu) {
        this.breakfastMenu = breakfastMenu;
        this.lunchMenu = lunchMenu;
    }

    public List getNewMenu() {
        return newMenu;
    }

    private void setNewMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            newMenu.add(iterator.next());
        }
    }

    public void setNewMenu() {
        Iterator breakfastMenuIterator = breakfastMenu.getMenu().iterator();
        setNewMenu(breakfastMenuIterator);
        setNewMenu(lunchMenu);
    }
}
