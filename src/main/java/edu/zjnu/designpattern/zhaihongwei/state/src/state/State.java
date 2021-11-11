package edu.zjnu.designpattern.zhaihongwei.state.src.state;

/**
 * Create by zhaihongwei on 2018/4/2
 * 抽象的状态对象
 */
public interface State {

    void operationByState(LightContext light);
}
