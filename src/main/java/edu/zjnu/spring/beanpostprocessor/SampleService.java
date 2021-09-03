package edu.zjnu.spring.beanpostprocessor;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-09-03
 **/
public class SampleService implements NameIniter, ISampleService {

    private String name;

    @Override
    public void say() {
        System.out.println("hello " + name);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
