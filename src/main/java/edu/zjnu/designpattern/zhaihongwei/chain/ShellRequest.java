package edu.zjnu.designpattern.zhaihongwei.chain;

/**
 * Create by zhaihongwei on 2018/3/29
 * 组装手机外壳请求
 */
public class ShellRequest implements Request {

    @Override
    public void request() {
        System.out.println("需要组装手机外壳啦！----->>>");
    }
}
