package edu.zjnu.designpattern.command;


/**
 * Create by zhaihongwei on 2018/3/30
 * 客户端对象
 */
public class Client {

    public static void main(String[] args) {
        // 创建小爱同学
        XiaoAi xiaoAi = new XiaoAi();
        // 创建具体的等对象，相当于具体的命令接受者
        Light light = new Light();
        // 创建了开灯的命令，你就是命令的发起者
        System.out.println("小爱同学帮我把灯开一下！");
        Command lightOnCommand = new LightOnCommand(light);
        // 小爱同学接受到了你发出的命令,并执行命令
        xiaoAi.setCommand(lightOnCommand);
        xiaoAi.doCommand();

        System.out.println("-------------------------------------------------");
        System.out.println("小爱同学帮我关一下灯！");
        Command lightOffCommand = new LightOffCommand(light);
        xiaoAi.setCommand(lightOffCommand);
        xiaoAi.doCommand();
    }
}
