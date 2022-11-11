package edu.zjnu.base.base.jvm.CGLIB;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: 杨海波
 * @date: 2022-11-08 16:23:10
 * @description: CGlibDynamicProxy
 */
public class CGlibDynamicProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if (objects[0] instanceof String) {
            objects[0] = "钟白浅";
        }


        Object object = methodProxy.invokeSuper(o, objects);

        return object;
    }

    static class PersonService {

        private String name;

        public PersonService() {
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback(new CGlibDynamicProxy());
        PersonService proxy = (PersonService) enhancer.create();
        proxy.setName("杨海波");

        System.out.println(proxy.name);
    }
}
