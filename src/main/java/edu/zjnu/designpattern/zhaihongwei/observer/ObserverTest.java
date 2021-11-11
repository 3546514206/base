package edu.zjnu.designpattern.zhaihongwei.observer;


/**
 * Create by zhaihongwei on 2018/3/26
 * 测试类
 */
public class ObserverTest {

    public static void main(String[] args) {
        // 创建我的blog对象
        MyBlog myBlog = new MyBlog();
        // 创建粉丝对象
        FansA fansA = new FansA();
        FansB fansB = new FansB();

        // A，B关注了我（点击了关注按钮），成为了我的粉丝
        myBlog.registerObserver(fansA);
        myBlog.registerObserver(fansB);

        // 我写了一篇新的Blog，并且确认博客可以发布了
        myBlog.setIsPublication();
        // 通知我的粉丝
        myBlog.notifyObserver();

        System.out.println("----------------------------------------");

        // 555... FanB不再关注我了，以后我写的blog不能通知他了
        myBlog.removeObserver(fansB);
        // 我又写了一篇新的Blog，通知我的粉丝
        myBlog.notifyObserver();
    }
}
