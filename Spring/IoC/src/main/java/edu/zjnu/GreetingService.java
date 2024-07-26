package edu.zjnu;

/*
 *
 * GreetingService
 *
 * @Date 2024-07-26 下午4:01
 * @Author 杨海波
 **/
public class GreetingService implements IGreetingService{

    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }

}
