package edu.zjnu.designpattern.zhaihongwei.visitor.visits;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class TreeNodeB implements TreeNode {

    @Override
    // 一次分派
    public void accept(Visitor visitor) {
        // 二次分派
        visitor.visitTreeNodeB(this);
    }
}
