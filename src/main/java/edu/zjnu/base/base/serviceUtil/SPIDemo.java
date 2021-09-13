package edu.zjnu.base.base.serviceUtil;

import java.util.ServiceLoader;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-09-13
 **/
public class SPIDemo {

    public static void main(String[] args) {
        ServiceLoader<IService> services = ServiceLoader.load(IService.class);
        for (IService service : services) {
            service.doService();
        }
    }
}