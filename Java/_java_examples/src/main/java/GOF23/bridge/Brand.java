package com.gqz.bridge;

/**
 * 品牌
 *
 * @author ganquanzhong
 * @ClassName: Brand
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月22日 下午4:35:42
 */
public interface Brand {
    void sale();
}

class Lenovo implements Brand {

    @Override
    public void sale() {
        System.out.println("销售联想电脑");
    }

}

class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售Dell电脑");
    }

}

class Shenzhou implements Brand {

    @Override
    public void sale() {
        System.out.println("销售神舟电脑");
    }

}
