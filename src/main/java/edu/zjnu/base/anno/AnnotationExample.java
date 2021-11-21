package edu.zjnu.base.anno;

import edu.zjnu.base.NullClass;

import java.lang.annotation.Annotation;

/**
 * @description: 注解测试
 * @author: 杨海波
 * @date: 2021-07-19
 **/
@NullClass
public class AnnotationExample {

    public static void main(String[] args) {
        AnnotationExample annotationExample = new AnnotationExample();
        Class aClass = annotationExample.getClass();
        Annotation[] annotation = aClass.getAnnotations();
        System.out.println(annotation.toString());
        System.out.println(annotation.length);
    }
}
