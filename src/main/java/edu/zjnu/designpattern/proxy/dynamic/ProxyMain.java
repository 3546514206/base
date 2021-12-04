package edu.zjnu.designpattern.proxy.dynamic;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-12-04
 **/
public class ProxyMain {

    public static void main(String[] args) {
        UserDao target = new UserDaoImpl();
        //输出目标对象信息
        System.out.println(target.getClass());
        //使用的不是实际的target对象（被代理对象），先需要获取代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        //输出代理对象信息
        System.out.println(proxy.getClass());
        //执行代理方法
        proxy.save();
    }
}
