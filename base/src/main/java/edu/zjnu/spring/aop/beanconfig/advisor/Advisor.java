package edu.zjnu.spring.aop.beanconfig.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * @author 杨海波
 * @description 通知器
 * @date 2021-02-16 12:23
 */
public class Advisor implements MethodInterceptor {

    private static Logger log = Logger.getLogger(MethodInterceptor.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("before advice");

        Object o = invocation.proceed();

        log.info("after advice");

        return o;
    }
}
