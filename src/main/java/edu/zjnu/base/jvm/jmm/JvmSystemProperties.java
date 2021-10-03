package edu.zjnu.base.jvm.jmm;

import java.util.Properties;

/**
 * @description: Jvm进程的系统属性
 * @author: 杨海波
 * @date: 2021-07-07
 **/
public class JvmSystemProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(properties);
    }
}
