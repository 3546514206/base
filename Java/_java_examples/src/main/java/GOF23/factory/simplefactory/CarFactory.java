package com.gqz.factory.simplefactory;

/**
 * 简单工厂类
 *
 * @author ganquanzhong
 * @ClassName: CarFactory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月22日 上午11:32:25
 */
public class CarFactory {

    //通过传入字段名，创建类
    public static Car createCar(String type) {
        if ("奥迪".equals(type)) {
            return new Audi();
        } else if ("比亚迪".equals(type)) {
            return new Byd();
        } else {
            return null;
        }
    }

}
