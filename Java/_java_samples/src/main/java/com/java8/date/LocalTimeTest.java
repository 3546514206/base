package com.java8.date;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author landyl
 * @create 5:30 PM 03/02/2018
 * LocalTime represents a time without a timezone, e.g. 10pm or 17:30:15.
 * The following example creates two local times for the timezones defined above.
 * Then we compare both times and calculate the difference in hours and minutes between both times.
 */
public class LocalTimeTest {
    public static void main(String[] args) {

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239

        //LocalTime comes with various factory method to simplify the creation of new instances, including parsing of time strings.

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("05:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

    }
}
