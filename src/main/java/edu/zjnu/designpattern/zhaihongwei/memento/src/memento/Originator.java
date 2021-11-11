package edu.zjnu.designpattern.zhaihongwei.memento.src.memento;

/**
 * Create by zhaihongwei on 2018/3/31
 * 发起者对象
 */
public class Originator {

    // 内部状态，可以是很多个。
    private String importantState;
    private String commonState;

    public String getImportantState() {
        return importantState;
    }

    public void setImportantState(String importantState) {
        this.importantState = importantState;
    }

    public String getCommonState() {
        return commonState;
    }

    public void setCommonState(String commonState) {
        this.commonState = commonState;
    }

    /**
     * 创建Memento对象，并且将需要保存的状态，保存到Memento对象中
     *
     * @return
     */
    public Memento createMemento() {
        return new Memento(importantState);
    }

    /**
     * 恢复到以前状态的方法
     *
     * @param memento
     */
    public void restoreState(Memento memento) {
        this.importantState = memento.getState();
    }

    /**
     * toString，为了测试
     *
     * @return
     */
    @Override
    public String toString() {
        return "Originator{" +
                "importantState='" + importantState + '\'' +
                ", commonState='" + commonState + '\'' +
                '}';
    }
}

