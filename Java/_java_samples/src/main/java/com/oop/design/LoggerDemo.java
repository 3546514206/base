package com.oop.design;

import java.util.logging.Logger;

/**
 * Created by Landy on 2019/1/6.
 */
public class LoggerDemo {

    static {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        //设计时需要注意的问题：
        //1.类加载的先后顺序问题
        //2.能够常量化的尽量常量化
        //3.多确认依赖的版本，如查看@since的版本
        //commons-lang 3.7
        //commons-lang 3.8
        //需要注意二进制（class/jar）兼容问题
        //在Maven库的管理中，尽量减少二方库的依赖
        System.out.println("World");

        //使用System.setProperty()有可能会设置不成功，可能会被JVM启动的时候修改掉
        //尽量使用-Djava.util.logging.config.file=...参数来初始化配置项
        //-D等同于System.setProperty()
        //System.setProperty("java.util.logging.config.file","D:\\projects\\idea_workspace\\example\\src\\main\\resources\\logging.properties");
        Logger logger = Logger.getLogger("com");
        logger.fine("Hello World");

    }

}
