package edu.zjnu.designpattern.zhaihongwei.observer;

/**
 * Create by zhaihongwei on 2018/3/26
 * 抽象观察者对象
 * 通过抽象的观察对象，将主题对象和观察者对象之间实现松耦合。
 */
public interface Observer {
    /**
     * 更新状态
     *
     * @param newBlog
     */
    void update(String newBlog);
}
