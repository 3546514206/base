package edu.zjnu.spring.aop.impl;

import edu.zjnu.spring.aop.IService;

/**
 * @author 杨海波
 * @description Service实现
 * @date 2021-02-16 12:17
 */
public class Service implements IService {

    public void doSomeThing() {
        log.info("do someThing!");
    }
}
