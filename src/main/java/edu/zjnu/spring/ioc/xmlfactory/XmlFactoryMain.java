package edu.zjnu.spring.ioc.xmlfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @description: XmlFactoryMain
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class XmlFactoryMain {
    /**
     * 直接使用BeanFactory作为容器
     */
    private static void beanFactory() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-ioc-xmlfactory.xml"));
        MyTestBean bean = (MyTestBean) beanFactory.getBean("myTestBean");
        //MyTestBean bean =  beanFactory.getBean(MyTestBean.class);
        System.out.println(bean.getTestStr());
    }

    public static void main(String[] args) {
        beanFactory();
    }
}
