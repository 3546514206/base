package edu.zjnu.base.base.ref;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-07-28 14:22
 **/
public class RefMain {

    public static void main(String[] args) {
        RefMain refMain = new RefMain();
        Ref ref = new Ref();
        refMain.f(ref);
        System.out.println(ref.testStr);
    }

    private  void f(Ref ref) {
        ref.setTestStr("1111");
    }
}
