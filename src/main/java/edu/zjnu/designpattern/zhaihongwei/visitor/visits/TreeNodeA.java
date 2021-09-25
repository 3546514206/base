package edu.zjnu.designpattern.visitor.visits;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class TreeNodeA implements TreeNode {

    @Override
    // 一次分派
    public void accept(Visitor visitor) {
        // 二次分派
        visitor.visitTreeNodeA(this);
    }
}
