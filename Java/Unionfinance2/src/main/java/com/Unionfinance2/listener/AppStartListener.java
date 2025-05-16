package com.Unionfinance2.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Application Lifecycle Listener implementation class AppStartListener
 *
 */
public class AppStartListener implements ServletContextListener {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL驱动加载成功");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败: " + e.getMessage());
        }
    }

    /**
     * Default constructor. 
     */
    public AppStartListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	String rootPath = arg0.getServletContext().getContextPath()+"/";
    	arg0.getServletContext().setAttribute("rootPath",rootPath);
    	    	
    }
	
}
