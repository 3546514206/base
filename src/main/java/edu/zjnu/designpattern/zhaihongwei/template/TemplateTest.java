package edu.zjnu.designpattern.zhaihongwei.template;

/**
 * Create by zhaihongwei on 2018/3/24
 */
public class TemplateTest {

    public static void main(String[] args) {
        SpicyFragrantPot spicyFragrantPotA = new CustomerA();
        spicyFragrantPotA.makingMethod();
        System.out.println("-----------------------------------");
        SpicyFragrantPot spicyFragrantPotB = new CustomerB();
        spicyFragrantPotB.makingMethod();
    }
}
