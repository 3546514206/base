package edu.zjnu.biz.listener;

import edu.zjnu.biz.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @description: WebContextLoadListener
 * @author: 杨海波
 * @date: 2022-05-19 08:49
 **/
public class WebContextLoadListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        if (null != context) {
            User user = (User) context.getBean("lsw");
            System.out.println(user.getName());
        }
        System.out.println("应用容器完成初始化");
    }

}
