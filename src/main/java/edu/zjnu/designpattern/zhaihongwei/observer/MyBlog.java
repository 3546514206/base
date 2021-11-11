package edu.zjnu.designpattern.zhaihongwei.observer;

import java.util.ArrayList;

/**
 * Create by zhaihongwei on 2018/3/26
 * 具体的主题对象
 * 我的博客类
 */
public class MyBlog implements Subject {

    // 确认博客是否能发布，默认为false
    private boolean isPublication;

    // 用来保存所有粉丝的容器
    private ArrayList<Observer> observers = new ArrayList();

    /**
     * 哎呀，又有一个新的粉丝了开心。我记住你了哟！！！
     *
     * @param observer
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 555...，失去了一个粉丝，你怎么可以取消关注呢！期待与你再次相遇！
     *
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 我写了一篇新的Blog，我要通知我所有的粉丝过来看。
     */
    @Override
    public void notifyObserver() {
        if (isPublication) {
            // 遍历所有的粉丝，并通知他们，我更新blog了
            for (Observer observer : observers) {
                observer.update("亲爱的" + observer.getClass() + ",我新写了一篇blog，记得来看哦！");
            }
        }
    }

    public boolean setIsPublication() {
        System.out.println("我已经确认博客可以发布了，快来看吧！");
        isPublication = true;
        return isPublication;
    }
}
