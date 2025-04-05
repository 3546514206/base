package com.oop.design;

import org.apache.commons.lang3.SystemUtils;

/**
 * Created by Landy on 2019/1/6.
 */
public class SystemPropertiesDemo {

    public static void main(String[] args) {
        //JVM启动的时候就设置好了
        //System.getProperties()尽可能的只读一次
        //但是，Properties是线程安全的，继承了HashTable，读和写都加了synchronized，效率会很低，怎么办？
        //http://commons.apache.org/
        //http://commons.apache.org/proper/commons-lang/
        //比如org.apache.commons.lang3.SystemUtils#FILE_ENCODING 可通过常量来读取
        //所以开源框架的设计综合考虑了很多方面的东西
        System.out.println(SystemUtils.FILE_ENCODING);//JVM启动的时候就加载了，省事
        System.out.println(System.getProperty("file.encoding"));
    }

}
