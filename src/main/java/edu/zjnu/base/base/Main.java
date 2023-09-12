package edu.zjnu.base.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        HashMap<String,String> map = new HashMap<>();
        map.put("blurname","cat");
        System.out.println(map instanceof Comparable);
    }
}
