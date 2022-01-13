package edu.zjnu.base.jvm;

/**
 * @description: 主方法
 * -XX:+UnlockDiagnosticVMOptions -XX:CompileCommand=print,Person.doSomeThing -XX:-UseCounterDecay
 * @author: 杨海波
 * @date: 2022-01-13
 **/
public class JitMain {

    public static void main(String[] args) {
        Person person = new Person();
        for (int i = 0; i < 100; i++) {
            person.doSomeThing();
        }
    }
}
