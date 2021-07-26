package edu.zjnu.spring.aop.dynamicProxy;

import edu.zjnu.SpringLoader;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 杨海波
 * @description 代理对象
 * @date 2021-02-17 18:33
 */
public class MyProxy implements InvocationHandler {

    Logger log = Logger.getLogger(SpringLoader.class);
    private InterfaceProxyed proxyed;

    public MyProxy(InterfaceProxyed proxyed) {
        this.proxyed = proxyed;
    }

    /**
     * 代理对象的实现
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before");
        Object invoke = method.invoke(proxyed, args);
        log.info("after");
        return invoke;
    }
}
