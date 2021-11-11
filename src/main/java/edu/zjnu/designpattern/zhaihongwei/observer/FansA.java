package edu.zjnu.designpattern.zhaihongwei.observer;

/**
 * Create by zhaihongwei on 2018/3/26
 * 粉丝A
 */
public class FansA implements Observer {

    @Override
    public void update(String newBlog) {
        System.out.println(newBlog);
    }
}
