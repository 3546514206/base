package edu.zjnu.designpattern.zhaihongwei.state.src.state;

/**
 * Create by zhaihongwei on 2018/4/2
 * 灯的关闭状态
 */
public class LightOffState implements State {

    @Override
    /**
     * 灯在关闭的情况下，再次按下开关，灯将打开
     */
    public void operationByState(LightContext light) {
        System.out.println("灯打开了！");
        // 灯关闭（一次点击开关按钮）之后，将灯的开关状态修改为开启状态
        light.state = new LightOnState();
    }
}
