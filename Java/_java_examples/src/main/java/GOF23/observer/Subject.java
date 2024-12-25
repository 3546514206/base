package com.gqz.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    protected List<Observer> list = new ArrayList<Observer>();

    public void registerObserver(Observer obs) {
        list.add(obs);
    }

    public void removeObserver(Observer obs) {
        list.add(obs);
    }

    //通知所有的观察者更新状态
    public void notifyAllObservers() {
        for (Observer obs : list) {
            obs.update(this);
        }
    }

}
