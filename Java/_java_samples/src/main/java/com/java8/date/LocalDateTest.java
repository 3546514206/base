package com.java8.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author landyl
 * @create 5:39 PM 03/02/2018
 * LocalDate represents a distinct date, e.g. 2014-03-11.
 * It's immutable and works exactly analog to LocalTime.
 * The sample demonstrates how to calculate new dates by adding or substracting days, months or years.
 * Keep in mind that each manipulation returns a new instance.
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    // FRIDAY
    }
}
