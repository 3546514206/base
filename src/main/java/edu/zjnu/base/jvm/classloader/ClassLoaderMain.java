package edu.zjnu.base.jvm.classloader;

/**
 * @description: 类加载机制
 * @author: 杨海波
 * @date: 2021-10-03
 **/
public class ClassLoaderMain {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoaderMain.class.getClassLoader();
        // 应用类加载器
        System.out.println(classLoader);
        // 扩展类加载器
        System.out.println(classLoader.getParent());
        // 启动类加载器是C++写的，输出为null
        System.out.println(classLoader.getParent().getParent());

        ClassLoader listLoader = Class.forName("java.util.List").getClassLoader();
        // 为什么是null？任何类的加载必然是有类加载器的，为Null,只有可能是启动类加载器
        System.out.println(listLoader);

        System.out.println("启动类加载器加载的目录：" + System.getProperty("sun.boot.class.path"));
        // 扩展类加载器加载的目录加载的目录，可以通过 -D java.ext.dirs 另行指定
        System.out.println("扩展类加载器加载的目录：" + System.getProperty("java.ext.dirs"));
        // 应用类加载器加载的目录加载的目录，可以通过 -D java.class.path 另行指定
        System.out.println("应用类加载器加载的目录：" + System.getProperty("java.class.path"));

    }
}
