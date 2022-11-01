package edu.zjnu.base.base;

import java.util.Calendar;

/**
 * @author: 杨海波
 * @date: 2022-11-01 09:22:37
 * @description: todo
 */
public class DateUtilMain {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month == 12) {
            year = ++year;
            month = 1;
        }
        String yearString = year + "" + (((month < 10)) ? "0" + month : month);
        System.out.println(yearString);
    }
}
