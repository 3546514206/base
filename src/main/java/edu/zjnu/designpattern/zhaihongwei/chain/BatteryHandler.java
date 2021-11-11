package edu.zjnu.designpattern.zhaihongwei.chain;

/**
 * Create by zhaihongwei on 2018/3/29
 * 组装电池的处理器
 */
public class BatteryHandler implements Handler {

    // 下一级处理器的引用
    private Handler nextHandler;

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (request instanceof BatteryRequest) {
            System.out.println(this.getClass() + ": 组装好了手机电池啦！");
        } else {
            // 如果手机电池处理器处理不了交给下一个处理器处理
            nextHandler.handleRequest(request);
        }
    }
}
