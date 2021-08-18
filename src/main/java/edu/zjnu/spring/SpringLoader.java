package edu.zjnu.spring;

import edu.zjnu.spring.annotation.PersonConfig;
import edu.zjnu.spring.aop.aopconfig.Swimable;
import edu.zjnu.spring.aop.beanconfig.Sleepable;
import edu.zjnu.spring.ioc.common.Person;
import edu.zjnu.spring.ioc.xmlfactory.MyTestBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
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
        //ioc();
        beanFactory();
        //  iocV2();
        //aopBean();
        //aopConfig();
        annotation();
    }

    /**
     * 直接使用BeanFactory作为容器
     */
    private static void beanFactory() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-ioc-xmlfactory.xml"));
        MyTestBean bean = (MyTestBean) beanFactory.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }

    /**
     * 通过<aop:config/>标签
     */
    private static void aopConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aop-conf.xml");
        Swimable duck = (Swimable) context.getBean("duck");
        duck.swim();
    }

    private static void iocV2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-ioc-common.xml");

        Person person = (Person) context.getBean("person");
        log.info(person.toString());
    }

    /**
     * 通过<bean/>标签配合ProxyFactoryBean的方式
     */
    private static void aopBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-bean-conf.xml");

        Sleepable me = (Sleepable) context.getBean("me");
        Sleepable meProxy = (Sleepable) context.getBean("meProxy");
        System.out.println("没有代理时：");
        me.sleep();

        System.out.println();
        System.out.println("AOP代之后：");
        meProxy.sleep();
    }

    /**
     * IoC测试入口
     */
    private static void ioc() {
        // bean定义文件的抽象
        Resource resource = new ClassPathResource("spring-ioc-common.xml");

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
