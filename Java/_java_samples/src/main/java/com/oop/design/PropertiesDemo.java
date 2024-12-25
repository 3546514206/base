package com.oop.design;

import org.apache.commons.lang3.SystemUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Landy on 2019/1/6.
 */
public class PropertiesDemo {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        InputStream is = PropertiesDemo.class.getResourceAsStream("/default.properties");
        //引起乱码
        //properties.load(is);
        //好的面向对象设计，需要考虑不同的方法重载，适应不同的来源 InputStreamReader三个重载构造器
        //适配器模式（前者转换后者，然后适配后者接口）：InputStream -> Reader
        //装饰器模式（两者具备相同的父接口）：InputStream -> ByteInputStream

        InputStreamReader isr = new InputStreamReader(is, SystemUtils.FILE_ENCODING);
        //入参尽可能地保证是接口
        properties.load(isr);

        System.out.println(properties.get("name"));
        System.out.println(properties.get("key")); //会乱码怎么办？

    }

}
