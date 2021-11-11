package edu.zjnu.designpattern.zhaihongwei.chain;

/**
 * Create by zhaihongwei on 2018/3/29
 * 客户端类
 */
public class Client {

    public static void main(String[] args) {

        System.out.println("--------发出请求：手机主板已经生产好了！需要组装屏幕，电池和外壳！---------");
        // 创建请求
        ScreenRequest screenRequest = new ScreenRequest();
        BatteryRequest batteryRequest = new BatteryRequest();
        ShellRequest shellRequest = new ShellRequest();

        // 创建处理器
        ScreenHandler screenHandler = new ScreenHandler();
        BatteryHandler batteryHandler = new BatteryHandler();
        ShellHandler shellHandler = new ShellHandler();

        // 将处理器连接成一条责任链（流水线）
        screenHandler.setNextHandler(batteryHandler);
        batteryHandler.setNextHandler(shellHandler);

        // 发送请求并处理,因为流水线上的工位都是固定的，
        // 所有的请求只需要传给责任链中的第一个处理器就可以了，责任链会自行决定由哪个处理器处理请求
        screenRequest.request();
        screenHandler.handleRequest(screenRequest);
        batteryRequest.request();
        screenHandler.handleRequest(batteryRequest);
        screenRequest.request();
        screenHandler.handleRequest(shellRequest);

        System.out.println("----------------------手机已经组装好了！交给手机接受者！--------------------");
        System.out.println("----------------------我是手机接受者，接受到了完整的手机--------------------");
    }
}
