package edu.zjnu;

/**
 * GreetingService
 *
 * @Date 2024-07-26 下午4:01
 * @Author 杨海波
 **/
public class HelloService implements IHelloService {

    private String name;

    @Override
    public String greet(String name) {
        return "Hello, " + name + "! " + "I`m " + this.name + "!";
    }

    public void setName(String name) {
        this.name = name;
    }
}
