package edu.zjnu.designpattern.zhaihongwei.memento.src.memento;

/**
 * Create by zhaihongwei on 2018/3/31
 * 负责人对象
 */
public class Caretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
