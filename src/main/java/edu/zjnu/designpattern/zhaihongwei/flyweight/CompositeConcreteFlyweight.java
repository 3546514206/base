package edu.zjnu.designpattern.zhaihongwei.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhaihongwei on 2018/3/22
 */
public class CompositeConcreteFlyweight implements Flyweight {

    private Map<String, Flyweight> compositeConcreteFlyweight = new HashMap<>();

    /**
     * 添加单纯的享元对象
     *
     * @param internalState 内部状态
     * @param flyweight     单纯的享元对象
     */
    public void putFlyweight(String internalState, Flyweight flyweight) {
        compositeConcreteFlyweight.put(internalState, flyweight);
    }

    /**
     * 获取复合享元对象中的一个单纯的享元对象
     *
     * @param internalState
     * @return
     */
    public Flyweight getFlyweight(String internalState) {
        return compositeConcreteFlyweight.get(internalState);
    }

    @Override
    public void operation(String externalState) {
        // 遍历复合的享元对象中包含的每个单纯的享元对象。
        for (String internalState : compositeConcreteFlyweight.keySet()) {
            Flyweight flyweight = compositeConcreteFlyweight.get(internalState);
            flyweight.operation(externalState);
        }
    }
}
