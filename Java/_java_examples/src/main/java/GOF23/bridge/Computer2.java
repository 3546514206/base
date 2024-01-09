package com.gqz.bridge;

/**
 * 电脑类型的维度
 */
public abstract class Computer2 {
    protected Brand brand;

    public Computer2(Brand b) {
        this.brand = b;
    }

    public void sale() {
        brand.sale();
    }

}

class Desktop2 extends Computer2 {
    public Desktop2(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售台式机");
    }
}


class Laptop2 extends Computer2 {
    public Laptop2(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售笔记本");
    }
}
