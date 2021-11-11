package edu.zjnu.designpattern.zhaihongwei.visitor.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/4/3
 */
public class ObjectStructure {

    private List<DigitalProduct> digitalProducts = new ArrayList<>();

    /**
     * 添加新的数码产品类型
     *
     * @param product
     */
    public void add(DigitalProduct product) {
        digitalProducts.add(product);
    }

    /**
     * 删除指定的数码产品类型
     *
     * @param product
     */
    public void remove(DigitalProduct product) {
        digitalProducts.remove(product);
    }

    public void doSomething(Visitor visitor) {
        for (DigitalProduct digitalProduct : digitalProducts) {
            digitalProduct.accept(visitor);
        }
    }
}
