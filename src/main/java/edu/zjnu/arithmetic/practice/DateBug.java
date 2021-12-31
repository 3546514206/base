package edu.zjnu.arithmetic.practice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: todo
 * @author:
 * @date: 2021-12-31
 **/
public class DateBug {

    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        DateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        //2021-12-31
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println(datef.format(date));
        System.out.println(dateFormat.parse("2021-12-31"));
        System.out.println(datef.parse("2021-12-31"));
    }
}
