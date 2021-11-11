package edu.zjnu.designpattern.zhaihongwei.observer;

/**
 * Create by zhaihongwei on 2018/3/26
 * 抽象的主题对象
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
