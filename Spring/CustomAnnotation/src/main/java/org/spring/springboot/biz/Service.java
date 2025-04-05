package org.spring.springboot.biz;


import org.spring.springboot.myannotation.CustomAnnotation;

/**
 * Service
 *
 * @Date 2024-10-28 15:32
 * @Author 杨海波
 **/
@CustomAnnotation
public class Service {

    public void doSomeThing() {
        System.out.println("doing something...");
    }
}
