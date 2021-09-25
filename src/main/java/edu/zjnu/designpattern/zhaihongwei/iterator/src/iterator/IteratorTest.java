package edu.zjnu.designpattern.iterator.src.iterator;

import java.util.List;

/**
 * Create by zhaihongwei on 2018/3/27
 */
public class IteratorTest {

    /*public static void main(String[] args) {

        // 创建聚合对象，并存入具体元素
        AggregateObject<Object> iterator = new AggregateObject<>();
        List<Object> list = iterator.list;
        list.add("一号");
        list.add("二号");
        list.add("三号");

        // 将聚合对象中的所有元素装入容器中
        ConcreteContainer<Object> container = new ConcreteContainer<>();
        MyIterator myIterator = container.getMyIterator(iterator);
        while (myIterator.hasNext()) {
            container.add(myIterator.next());
        }

        // 遍历容器中的所有元素
        for (Object cont : container.list) {
            System.out.println(cont);
        }
    }*/

    public static void main(String[] args) {
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        breakfastMenu.setMenu();
        LunchMenu lunchMenu = new LunchMenu();
        lunchMenu.setMenu();
        NewMenu2 newMenu2 = new NewMenu2(breakfastMenu, lunchMenu);
        newMenu2.setNewMenu();
        List newMenu = newMenu2.getNewMenu();
        for (Object item : newMenu) {
            System.out.println(item);
        }
    }
}
