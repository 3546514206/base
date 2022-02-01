package edu.zjnu.designpattern.template;

/**
 * @description: 测试入口
 * @author: 杨海波
 * @date: 2022-02-01
 **/
public class TemplateMain {

    public static void main(String[] args) {
        ArithmeticTemplate template1 = new FirstArithmetic();
        template1.arithmeticTemp();
        ArithmeticTemplate template2 = new SecondArithmetic();
        template2.arithmeticTemp();
    }
}
