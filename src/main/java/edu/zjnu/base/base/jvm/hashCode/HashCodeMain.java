package edu.zjnu.base.base.jvm.hashCode;

/**
 * @author: 杨海波
 * @date: 2022-12-30 17:17:37
 * @description: HashCodeMain
 */
public class HashCodeMain {

    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.hashCode());
        person.setName("杨海波");
        System.out.println(person.hashCode());
        person.setName("刘亦菲");
        System.out.println(person.hashCode());
    }
}
