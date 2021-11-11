package edu.zjnu.designpattern.zhaihongwei.command;

/**
 * Create by zhaihongwei on 2018/3/30
 * 关灯命令
 */
public class LightOffCommand implements Command {

    private Light light;

    /**
     * 创建关灯命令的时候，传入具体的灯对象，由灯对象操作自己
     *
     * @param light
     */
    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    /**
     * 具体的灯对象调用自己的关灯方法
     */
    public void execute() {
        light.lightOff();
    }
}
