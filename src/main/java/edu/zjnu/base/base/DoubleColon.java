package edu.zjnu.base.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2022-01-28
 **/
public class DoubleColon {
    @Test
    public void test() {
        List<String> list = Arrays.asList("aaaa", "bbbb", "cccc");

        //对象实例语法	instanceRef::methodName

    }

    private void doSomeThing(){

    }

    public void print(String content){
        System.out.println(content);
    }
}

