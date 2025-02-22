package com.gqz.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ganquanzhong
 * @ClassName: StarHandler
 * @Description: dynamic proxy使用JDK自带的动态代理
 * @date 2019年7月22日 下午3:53:16
 */
public class StarHandler implements InvocationHandler {

    //真实对象
    Star realStar;

    public StarHandler(Star realStar) {
        super();
        this.realStar = realStar;
    }

    //调用的方法都进入invoke里面

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;

        System.out.println("真正的方法执行前！");
        System.out.println("面谈，签合同，预付款，订机票");

        if (method.getName().equals("sing")) {
            object = method.invoke(realStar, args);
        }

        System.out.println("真正的方法执行后！");
        System.out.println("收尾款");
        return object;
    }

}
