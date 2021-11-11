package edu.zjnu.designpattern.zhaihongwei.visitor.visitor;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public interface Visitor {

    void visit(Phone phone);

    void visit(Camera camera);
}
