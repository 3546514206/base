package edu.zjnu.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-13
 **/
public class FizzBuzzMain {

    public static void main(String[] args) {
        System.out.println(new FizzBuzzMain().fizzBuzz(15));
    }

    public List<String> fizzBuzz(int n) {
        List<String> rs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                rs.add("FizzBuzz");
                continue;
            }
            if (i % 3 == 0) {
                rs.add("Fizz");
                continue;
            }
            if (i % 5 == 0) {
                rs.add("Buzz");
                continue;
            }

            rs.add(Integer.toString(i));
        }

        return rs;
    }
}
