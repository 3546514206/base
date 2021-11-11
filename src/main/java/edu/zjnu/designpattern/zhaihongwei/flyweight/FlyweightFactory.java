package edu.zjnu.designpattern.zhaihongwei.flyweight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by zhaihongwei on 2018/3/22
 */
public class FlyweightFactory {

    /**
     * 通过HashMap作为容器，保存具体的享元对象
     */
    private Map<String, Flyweight> flyweightMap = new HashMap<>();

    /**
     * 获取单纯的享元对象
     *
     * @param internalState 内部状态
     * @return
     */
    public Flyweight getFlyweight(String internalState) {
        // 先从HashMap容器中寻找有没有
        Flyweight flyweight = flyweightMap.get(internalState);
        // 这里没有考虑对线程同时访问的情况，具体可以使用单例模式。
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight(internalState);
            flyweightMap.put(internalState, flyweight);
        }
        return flyweight;
    }

    /**
     * 获取复合的享元对象
     *
     * @param internalStates 多个单纯的享元对象的内部状态
     * @return
     */
    public Flyweight getCompositeFlyweight(List<String> internalStates) {
        CompositeConcreteFlyweight ccFlyweight = new CompositeConcreteFlyweight();
        for (String internalState : internalStates) {
            ccFlyweight.putFlyweight(internalState, getFlyweight(internalState));
        }
        return ccFlyweight;
    }

}
