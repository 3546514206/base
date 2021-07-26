package edu.zjnu.spring.aop.dynamicProxy;

/**
 * @author 杨海波
 * @description
 * @date 2021-02-17 18:26
 */
public class InterfaceProxyedImpl implements InterfaceProxyed {

    public void doSomething() {
        log.info("do something");
    }
}
