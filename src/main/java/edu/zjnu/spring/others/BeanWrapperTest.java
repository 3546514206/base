package edu.zjnu.spring.others;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

/**
 * @description: BeanWrapper是Spring提供的一个用来操作JavaBean属性的工具，使用它可以直接修改一个对象的属性
 * @author: 杨海波
 * @date: 2021-08-27
 **/
public class BeanWrapperTest {

    public static void main(String[] args) {
        Person person = new Person();

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(person);
        beanWrapper.setPropertyValue("name", "杨海波");
        System.out.println(person.getName());

        PropertyValue value = new PropertyValue("name", "新垣结衣");
        beanWrapper.setPropertyValue(value);
        System.out.println(person.getName());
    }
}
