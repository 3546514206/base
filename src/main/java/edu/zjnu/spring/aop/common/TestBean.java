package edu.zjnu.spring.aop.common;

/**
 * @description: TestBean
 * @author: 杨海波
 * @date: 2021-09-04
 **/
public class TestBean {

    public String testString;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public void test(){
        System.out.println("this is a test ");
    }
}
