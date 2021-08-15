package edu.zjnu.designpattern.visitor.visit;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class SuccessVisitor implements Visitor {

    @Override
    public void getManState(Man man) {
        System.out.println("男人成功时，背后多半有一个伟大的女人");
    }

    @Override
    public void getWomanState(Woman woman) {
        System.out.println("女人成功时，背后多半有一个失败的男人");
    }
}
