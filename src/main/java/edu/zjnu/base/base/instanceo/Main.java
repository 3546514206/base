package edu.zjnu.base.base.instanceo;

/**
 * @author: 杨海波
 * @date: 2022-12-16 08:35:13
 * @description: todo
 */
public class Main {

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c instanceof C);
        System.out.println(c instanceof B);
        System.out.println(c instanceof A);

        D d = new D();
        B b = (B) d;
        System.out.println(b instanceof C);
    }
}
