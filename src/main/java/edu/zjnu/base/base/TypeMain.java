package edu.zjnu.base.base;

import java.util.ArrayList;
import java.util.List;

public class TypeMain {

    public static void main(String[] args) {
//        对于 List<Object> 来说，它实际上确定了 List 中包含的是 Object 及其子类,
        List<Object> list = new ArrayList<>();
        list.add("111");
        System.out.println(list.get(0));
    }
}
