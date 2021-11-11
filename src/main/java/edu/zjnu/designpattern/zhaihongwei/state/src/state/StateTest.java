package edu.zjnu.designpattern.zhaihongwei.state.src.state;

/**
 * Create by zhaihongwei on 2018/4/2
 */
public class StateTest {

    public static void main(String[] args) {

        // 创建灯对象
        LightContext lightContext = new LightContext();
        // 按一次开关
        lightContext.onoffSwitch();
        // 再按一次开关
        lightContext.onoffSwitch();
    }
}
