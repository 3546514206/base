package edu.zjnu.demo2;


/**
 * ConcreteComponent 具体构建
 *
 * @Date 2024-12-27 10:25
 * @Author 杨海波
 **/
public class ConcreteComponent implements Component {

    @Override
    public void operator() {
        System.out.println("doSomething");
    }
}
