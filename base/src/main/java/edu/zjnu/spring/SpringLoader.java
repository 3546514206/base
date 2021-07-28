package edu.zjnu.spring;

import edu.zjnu.spring.annotation.PersonConfig;
import edu.zjnu.spring.aop.IService;
import edu.zjnu.spring.ioc.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author 杨海波
 * @description Spring 框架启动入口
 * @date 2021-02-13 00:52
 */
public class SpringLoader {

    private static Logger log = Logger.getLogger(SpringLoader.class);

    public static void main(String[] args) {

        int[] var = new int[4];

        log.info(var instanceof Object);
        log.info(var.getClass());

//        ioc();
//        iocV2();
        aop();
//        annotation();
    }

    private static void iocV2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-ioc.xml");

        Person person = (Person) context.getBean("person");
        log.info(person.toString());
    }

    /**
     * AOP测试入口
     */
    private static void aop() {
        // 创建工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 工厂的相关初始化
        new XmlBeanDefinitionReader(factory).loadBeanDefinitions(new ClassPathResource("spring-aop.xml"));

        // proxyService就是代理对象
        IService proxyService = (IService) factory.getBean("aop");

        // 执行代理对象的方法，不仅会执行被代理对象的方法，还会执行切面
        proxyService.doSomeThing();

        // 代理对象的类型
        log.info(proxyService.getClass());
        // 代理对象的父类
        log.info(proxyService.getClass().getSuperclass());
        // 代理对象实现的接口
        for (Class<?> anInterface : proxyService.getClass().getInterfaces()) {
            log.info(anInterface.toString());
        }

    }

    /**
     * IoC测试入口
     */
    private static void ioc() {
        // bean定义文件的抽象
        Resource resource = new ClassPathResource("spring-ioc.xml");

        //  bean工厂：默认的可列举工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 实例化bean定义读取器：与工厂关联——读取器读取的bean信息将赋予该指定工厂
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        // 为读取器加载bean定义的xml资源文件
        reader.loadBeanDefinitions(resource);

        Person person = (Person) factory.getBean("person");
        log.info(person.toString());
    }

    /**
     * 注解的Spring使用方式
     */
    private static void annotation() {
        // 相当于Factory
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Bean的配置文件
        context.register(PersonConfig.class);
        context.refresh();

        //******************************** 以上仅仅是完成了Bean定义的装配 ********************************//

        //获取Bean
        Person person = (Person) context.getBean("person");
        log.info(person.toString());
    }

}
