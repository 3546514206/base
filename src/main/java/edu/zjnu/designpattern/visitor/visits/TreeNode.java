package edu.zjnu.designpattern.visitor.visits;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public interface TreeNode {

    void accept(Visitor visitor);
}
