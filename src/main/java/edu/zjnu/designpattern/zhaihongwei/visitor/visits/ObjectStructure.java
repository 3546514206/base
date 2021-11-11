package edu.zjnu.designpattern.zhaihongwei.visitor.visits;


import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/4/3
 * 保存所有同类树节点的容器
 */
public class ObjectStructure {

    private List<TreeNode> treeNodes = new ArrayList<>();

    /**
     * 添加新的树节点
     *
     * @param treeNode
     */
    public void add(TreeNode treeNode) {
        treeNodes.add(treeNode);
    }

    /**
     * 删除指定的树节点
     *
     * @param treeNode
     */
    public void remove(TreeNode treeNode) {
        treeNodes.remove(treeNode);
    }


    /**
     * 遍历不同节点的，所做的不同的事
     *
     * @param visitor
     */
    public void doSomething(Visitor visitor) {
        for (TreeNode treeNode : treeNodes) {
            treeNode.accept(visitor);
        }
    }
}
