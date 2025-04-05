package com.gqz.iterator;

public class Client {

    public static void main(String[] args) {
        ConcreteMyAggregate cma = new ConcreteMyAggregate();
        cma.addObject("aa");
        cma.addObject("bb");
        cma.addObject("cc");

        MyIterator iter = cma.createIterator();
        while (iter.hasNext()) {
            System.out.println(iter.getCurrentObj());
            iter.next();
        }

    }
}
