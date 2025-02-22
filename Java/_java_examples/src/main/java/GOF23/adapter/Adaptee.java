package com.gqz.adapter;

/**
 * 被适配的类
 * 相当于只有PS/2接口的键盘
 *
 * @author ganquanzhong
 * @ClassName: Adaptee
 * @Description: 被适配的类
 * @date 2019年7月22日 下午3:11:20
 */
public class Adaptee {

    public void request() {
        System.out.println("可以完成客户请求的需要的功能！");
    }
}