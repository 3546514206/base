package com.gqz.prototype;

import java.util.Date;

/**
 * 原型模式(深复制)
 *
 * @author ganquanzhong
 * @ClassName: Client2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月22日 下午2:14:46
 */
public class Client2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date(12312321331L);
        Sheep2 s1 = new Sheep2("少利", date);
        Sheep2 s2 = (Sheep2) s1.clone();
        //实现深复制。s2对象的birthday是一个新对象！


        System.out.println(s1);
        System.out.println(s1.getSname());
        System.out.println(s1.getBirthday());

        date.setTime(23432432423L);

        System.out.println(s1.getBirthday());

        System.out.println("========================");
        s2.setSname("多利");
        System.out.println(s2);
        System.out.println(s2.getSname());
        System.out.println(s2.getBirthday());


    }
}
