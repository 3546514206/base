package edu.zjnu.designpattern.visitor.visit;


import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/4/3
 * 保存人类性别的容器
 */
public class ObjectStructure {

    private List<Human> humanList = new ArrayList<>();

    /**
     * 添加人类性别
     *
     * @param human
     */
    public void add(Human human) {
        humanList.add(human);
    }

    /**
     * 删除指定的人类性别
     *
     * @param human
     */
    public void remove(Human human) {
        humanList.remove(human);
    }

    /**
     * 遍历所有性别的人类对于相同的访问者，所做的不同反应
     *
     * @param visitor
     */
    public void doSomething(Visitor visitor) {
        for (Human human : humanList) {
            human.accept(visitor);
        }
    }
}
