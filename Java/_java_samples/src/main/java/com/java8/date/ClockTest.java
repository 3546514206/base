package com.java8.date;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * @author landyl
 * @create 5:07 PM 03/02/2018
 * Clock provides access to the current date and time.
 * Clocks are aware of a timezone and may be used instead of System.currentTimeMillis() to retrieve the current milliseconds.
 * Such an instantaneous point on the time-line is also represented by the class Instant.
 * Instants can be used to create legacy java.util.Date objects.
 */
public class ClockTest {
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        System.out.println("millis:" + millis + ":" + System.currentTimeMillis());

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);   // legacy java.util.Date

        System.out.println("legacy java.util.Date:" + legacyDate.toString());

    }

}
