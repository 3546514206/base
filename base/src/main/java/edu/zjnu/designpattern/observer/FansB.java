package edu.zjnu.designpattern.observer;

/**
 * Create by zhaihongwei on 2018/3/26
 * 粉丝B
 */
public class FansB implements Observer {

    @Override
    public void update(String newBlog) {
        System.out.println(newBlog);
    }
}
