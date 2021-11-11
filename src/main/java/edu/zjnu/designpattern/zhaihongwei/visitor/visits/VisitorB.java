package edu.zjnu.designpattern.zhaihongwei.visitor.visits;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class VisitorB implements Visitor {

    @Override
    public void visitTreeNodeA(TreeNodeA nodeA) {
        System.out.println("这是树节点A对于访问者B，所做的事");
    }

    @Override
    public void visitTreeNodeB(TreeNodeB nodeB) {
        System.out.println("这是树节点B对于访问者B，所做的事");
    }
}
