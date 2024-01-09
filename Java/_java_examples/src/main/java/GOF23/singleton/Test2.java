package com.gqz.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 测试反射和反序列化破解单例模式
 *
 * @author ganquanzhong
 * @ClassName: Client2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月19日 下午2:58:31
 */
public class Test2 {

    public static void main(String[] args) throws Exception {
        SingletonDemo6 s1 = SingletonDemo6.getInstance();
        SingletonDemo6 s2 = SingletonDemo6.getInstance();

        System.out.println(s1);
        System.out.println(s2);

        //通过反射的方式直接调用私有构造器
        System.out.println("=======通过反射的方式直接调用私有构造器=========");
        Class<SingletonDemo6> clazz = (Class<SingletonDemo6>) Class.forName("com.gqz.singleton.SingletonDemo6");
        Constructor<SingletonDemo6> c = clazz.getDeclaredConstructor(null);
        c.setAccessible(true); //设置不检查
        SingletonDemo6 s3 = c.newInstance();
        SingletonDemo6 s4 = c.newInstance();
        System.out.println(s3);
        System.out.println(s4);


        //通过反序列化的方式构造多个对象     解决方案  判断是否为空，抛出异常！
        System.out.println("=======通过反序列化的方式构造多个对象=========");
        FileOutputStream fos = new FileOutputStream("E:/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);//序列化s1对象
        oos.close();
        fos.close();

        //反序列化               解决方案 在类中定义readObject方法，直接返回该对象。
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:/a.txt"));
        //使用反序列化获取对象
        SingletonDemo6 s5 = (SingletonDemo6) ois.readObject();
        System.out.println(s5);


    }
}
