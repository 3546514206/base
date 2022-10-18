package edu.zjnu.base.base;

import java.util.ArrayList;
import java.util.List;

public class TypeMain {

    public static void main(String[] args) {
//        case1();
//        case2();
//        case3();
        case4();
    }

    private static void case1(){
        // 对于 List<Object> 来说，它实际上确定了 List 中包含的是 Object 及其子类。
        List<Object> list = new ArrayList<>();
        list.add("111");
        // 不报错 Integer 也是 Object 的子类。
        list.add(new Integer(2));
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    private static void case2(){
        List<?> list = new ArrayList<>();
        // JDK 8 报错，list 的泛型类型必须是传入的类型
        // list.add("111");
        // 报错，List<?> 则表示其中所包含的元素类型是不确定，其中可能包含的是 String，也可能是 Integer。但是只能是其一。
        // list.add(new Integer(2));
        // System.out.println(list.get(0));
        // System.out.println(list.get(1));

    }

    private static void case3(){
        List<? extends Number> numberArray = new ArrayList<>();
        //  报错，List<? extends Number> numberArray 可以理解为只是声明了一个类型，这个类型有一个类型参数，该类型参数必须是 Number 或其子类
        //  但是实际申请内的时候（= new ArrayList<>();），必须要确定下类型。
        //  numberArray.add(new Long(1L));
    }

    private static void case4(){
        List<? extends Number> numberArray;
        List<Long> longList = new ArrayList<>();
        longList.add(1l);
        longList.add(2l);
        numberArray = longList;
        System.out.println(numberArray.get(0));
    }
}
