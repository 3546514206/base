package edu.zjnu.base.string;

import java.util.regex.Pattern;

/**
 * @description: 正则例子
 * @author: 杨海波
 * @date: 2021-07-16
 **/
public class PatternExample {

    // 数字正则
    public static final Pattern pattern = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");

    public static void main(String[] args) {
        System.out.println(new PatternExample().isDigit());
    }

    private Boolean isDigit() {
        return pattern.matcher("1.2").matches();
    }
}
