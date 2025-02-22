package com.gqz.prototype;

import java.util.Date;

/**
 * prototype  原型模式
 * <p>
 * clone中的欠克隆
 *
 * @author ganquanzhong
 * @ClassName: Client
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月22日 下午2:06:40
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Date date = new Date(12312321331L);
        Sheep s1 = new Sheep("少利", date);
        //克隆一份
        Sheep s2 = (Sheep) s1.clone();

        System.out.println(s1);
        System.out.println(s1.getSname());
        System.out.println(s1.getBirthday());

        //由于是浅克隆  原型和克隆出来的新对象都是对同一个date的使用
        date.setTime(23432432423L);
        System.out.println(s1.getBirthday());
        System.out.println("========================");
        s2.setSname("多利");
        System.out.println(s2);
        System.out.println(s2.getSname());
        System.out.println(s2.getBirthday());


    }
}
