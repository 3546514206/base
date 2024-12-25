package com.gqz.factory.simplefactory;

/**
 * 创建具体的类时，需要与类，接口建立联系！
 *
 * @author ganquanzhong
 * @ClassName: Client01
 * @Description: 测试在没有工厂模式的情况下
 * @date 2019年7月19日 下午5:40:16
 */
public class Client01 {   //调用者

    public static void main(String[] args) {
        Car c1 = new Audi();
        Car c2 = new Byd();

        c1.run();
        c2.run();

    }
}
