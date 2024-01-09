package com.gqz.composite;

/**
 * 组合模式    树形结构
 * 当容器对象的指定方法被调用时 ，将遍历整个的树形结构， 寻找也包含这个方法的成员并调用执行。其中，使用了递归调用的机制 对整个结构进行处理
 *
 * @author ganquanzhong
 * @ClassName: Component
 * @Description: 抽象组件 组合模式为了处理树形结构提供了完美的解决方案
 * 描述了如何将容器和叶子进行递归组合，使得用户在使用时可以一致性的对待容器和叶子
 * @date 2019年7月22日 下午5:09:51
 */
public interface Component {
    void operation();
}

//叶子组件
interface Leaf extends Component {

}

//容器组件
interface Composite extends Component {
    void add(Component c);

    void remove(Component c);

    Component getChild(int index);
}