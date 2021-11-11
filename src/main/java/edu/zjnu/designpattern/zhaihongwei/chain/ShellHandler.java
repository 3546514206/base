package edu.zjnu.designpattern.zhaihongwei.chain;

/**
 * Create by zhaihongwei on 2018/3/29
 * 组装外壳处理器
 */
public class ShellHandler implements Handler {

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
        if (request instanceof ShellRequest) {
            System.out.println(this.getClass() + ": 组装好了手机外壳啦！");
        } else {
            // 如果手机外壳处理器处理不了交给下一个处理器处理
            nextHandler.handleRequest(request);
        }
    }
}
