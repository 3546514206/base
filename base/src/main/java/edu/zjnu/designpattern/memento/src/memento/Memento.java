package edu.zjnu.designpattern.memento.src.memento;

/**
 * Create by zhaihongwei on 2018/3/31
 * 备忘录对象
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
