package edu.zjnu.designpattern.command;

/**
 * Create by zhaihongwei on 2018/3/30
 * 开灯命令
 */
public class LightOnCommand implements Command {

    private Light light;

    /**
     * 创建开灯命令的时候，传入具体的灯对象，由灯对象操作自己
     *
     * @param light
     */
    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    /**
     * 具体的灯对象调用自己的开灯方法
     */
    public void execute() {
        light.lightOn();
    }
}
