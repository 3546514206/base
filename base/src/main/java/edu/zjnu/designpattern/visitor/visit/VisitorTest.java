package edu.zjnu.designpattern.visitor.visit;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class VisitorTest {

    public static void main(String[] args) {
        // 创建保存人类性别的
        ObjectStructure objectStructure = new ObjectStructure();

        // 创建男人和女人
        Human man = new Man();
        Human woman = new Woman();

        objectStructure.add(man);
        objectStructure.add(woman);

        // 创建成功的访问者对象
        Visitor successVisitor = new SuccessVisitor();

        objectStructure.doSomething(successVisitor);
    }
}
