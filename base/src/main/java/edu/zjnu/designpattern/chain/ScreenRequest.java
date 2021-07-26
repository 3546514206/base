package edu.zjnu.designpattern.chain;

/**
 * Create by zhaihongwei on 2018/3/29
 * 组装手机屏幕请求
 */
public class ScreenRequest implements Request {

    public void request() {
        System.out.println("需要组装手机屏幕啦！----->>>");
    }
}
