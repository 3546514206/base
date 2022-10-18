package edu.zjnu.base.base;

import java.util.ArrayList;
import java.util.List;

public class ContainMain {

    public static void main(String[] args) {
        String testStr = "a";

        List<String> list = new ArrayList<>();
        list.add(new String("a"));
        list.add(new String("b"));
        list.add(new String("c"));

        System.out.println(list.contains(testStr));
    }
}
