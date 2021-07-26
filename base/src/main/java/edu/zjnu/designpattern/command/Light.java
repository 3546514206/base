package edu.zjnu.designpattern.command;

/**
 * Create by zhaihongwei on 2018/3/30
 * 具体的电灯类
 */
public class Light {

    /**
     * 开灯方法
     */
    public void lightOn() {
        System.out.println("灯打开了！！");
    }

    /**
     * 关灯方法
     */
    public void lightOff() {
        System.out.println("灯关上了！！");
    }
}
