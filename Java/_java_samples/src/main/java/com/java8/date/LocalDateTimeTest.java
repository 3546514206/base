package com.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @author landyl
 * @create 5:41 PM 03/02/2018
 * LocalDateTime represents a date-time.
 * It combines date and time as seen in the above sections into one instance.
 * LocalDateTime is immutable and works similar to LocalTime and LocalDate.
 * We can utilize methods for retrieving certain fields from a date-time:
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439


        // With the additional information of a timezone it can be converted to an instant.
        // Instants can easily be converted to legacy dates of type java.util.Date.
        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014


        // Formatting date-times works just like formatting dates or times.
        // Instead of using pre-defined formats we can create formatters from custom patterns.

        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13
        // Unlike java.text.NumberFormat the new DateTimeFormatter is immutable and thread-safe.
        //
        //For details on the pattern syntax read here.
    }
}
