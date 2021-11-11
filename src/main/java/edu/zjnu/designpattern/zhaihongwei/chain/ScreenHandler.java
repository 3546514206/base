package edu.zjnu.designpattern.zhaihongwei.chain;

/**
 * Create by zhaihongwei on 2018/3/29
 * 组装屏幕的处理器
 */
public class ScreenHandler implements Handler {

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
        if (request instanceof ScreenRequest) {
            System.out.println(this.getClass() + ": 组装好了手机屏幕啦！");
        } else {
            // 如果手机屏幕处理器处理不了交给下一个处理器处理
            nextHandler.handleRequest(request);
        }
    }
}
