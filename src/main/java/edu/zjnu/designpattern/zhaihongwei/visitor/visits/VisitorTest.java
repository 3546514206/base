package edu.zjnu.designpattern.zhaihongwei.visitor.visits;

/**
 * Create by zhaihongwei on 2018/4/3
 * 测试类
 */
public class VisitorTest {

    public static void main(String[] args) {
        // 创建容器对象，用来添加新的节点
        ObjectStructure structure = new ObjectStructure();

        // 创建A，B两种树节点，可以添加多个，但是多个就失去了访问者模式的优势。
        TreeNode nodeA = new TreeNodeA();
        TreeNode nodeB = new TreeNodeB();

        // 向容器中添加A，B节点
        structure.add(nodeA);
        structure.add(nodeB);

        // 创建访问者A对象
        Visitor visitorA = new VisitorA();
        // A，B两种节点对象访问者A做出的反应
        structure.doSomething(visitorA);
        System.out.println("----------------------------------");
        // 创建访问者A对象
        Visitor visitorB = new VisitorB();
        // A，B两种节点对象访问者B做出的反应
        structure.doSomething(visitorB);
    }
}
