package edu.zjnu.designpattern.zhaihongwei.command;

/**
 * Create by zhaihongwei on 2018/3/30
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
        System.out.println("你想让我做什么呢？？");
    }
}
