package edu.zjnu.spring.aop.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author 杨海波
 * @description JDK动态代理
 * @date 2021-02-17 18:31
 */
public class DynamicProxy {

    public static void main(String[] args) {
        InterfaceProxyed subject = new InterfaceProxyedImpl();
        InvocationHandler subjectProxy = new MyProxy(subject);
        InterfaceProxyed proxyInstance = (InterfaceProxyed) Proxy.newProxyInstance(subjectProxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectProxy);
        proxyInstance.doSomething();
    }
}
