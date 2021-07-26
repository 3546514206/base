package edu.zjnu.designpattern.state.src.state;

/**
 * Create by zhaihongwei on 2018/4/2
 * 灯的开启状态
 */
public class LightOnState implements State {

    @Override
    /**
     * 灯在打开的情况下，再次按下开关，灯将关闭
     */
    public void operationByState(LightContext light) {
        System.out.println("灯关闭了！");
        // 灯打开（一次点击开关按钮）之后，将灯的开关状态修改为关闭状态
        light.state = new LightOffState();
    }
}
