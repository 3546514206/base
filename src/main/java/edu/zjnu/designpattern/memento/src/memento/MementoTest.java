package edu.zjnu.designpattern.memento.src.memento;

/**
 * Create by zhaihongwei on 2018/3/31
 */
public class MementoTest {

    public static void main(String[] args) {

        Originator originator = new Originator();
        originator.setImportantState("重要属性");
        originator.setCommonState("普通属性");
        System.out.println(originator);
        Memento memento = originator.createMemento();

        // 将Memento对象保存起来
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(memento);

        originator.setImportantState("改变的重要属性");
        originator.setCommonState("改变了普通属性");
        System.out.println(originator);

        System.out.println("操作失误，重要属性不能修改，我需要恢复");
        originator.restoreState(caretaker.getMemento());
        System.out.println(originator);
    }
}
