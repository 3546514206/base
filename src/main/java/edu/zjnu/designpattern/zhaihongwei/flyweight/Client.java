package edu.zjnu.designpattern.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongwei on 2018/3/22
 */
public class Client {

    public static void main(String[] args) {
        
        /*FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight = factory.getFlyweight("first_internalState");
        flyweight.operation("first_externalState");
        System.out.println(flyweight);
        System.out.println("--------------------------------------------");
        flyweight.operation("second_externalState");
        System.out.println(flyweight);*/

        FlyweightFactory factory = new FlyweightFactory();
        List<String> internalStates = new ArrayList<>();
        internalStates.add("first_internalState");
        internalStates.add("second_internalState");

        CompositeConcreteFlyweight compositeFlyweightA = (CompositeConcreteFlyweight) factory.getCompositeFlyweight(internalStates);
        CompositeConcreteFlyweight compositeFlyweightB = (CompositeConcreteFlyweight) factory.getCompositeFlyweight(internalStates);
        System.out.println("compositeFlyweightA和compositeFlyweightB是和可以共享的嘛？" + (compositeFlyweightA == compositeFlyweightB));
        System.out.println("-----------------------------------------------");
        System.out.println("compositeFlyweightA中的first_internalState和compositeFlyweightB中的first_internalState是可以共享的嘛？" + (compositeFlyweightA.getFlyweight(internalStates.get(0)) == compositeFlyweightB.getFlyweight(internalStates.get(0))));
    }
}
