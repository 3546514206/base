package edu.zjnu.designpattern.state.src.state;

/**
 * Create by zhaihongwei on 2018/4/2
 * Context对象
 */
public class LightContext {

    public State state;

    public LightContext() {
        // 灯的默认状态为关闭状态
        this.state = new LightOffState();
    }

    /**
     * 灯的开关，外界没有办法改变灯的打开或者关闭
     * 由灯的状态自己改变
     */
    public void onoffSwitch() {
        state.operationByState(this);
    }
}
