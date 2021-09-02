package edu.zjnu.spring.ioc.lookup;

/**
 * @description: GetBeanTest
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public abstract class GetBeanTest {

    public void showMe() {
        this.getBean().showMe();
    }

    public abstract User getBean();
}
