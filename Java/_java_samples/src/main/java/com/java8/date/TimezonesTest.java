package com.java8.date;

import java.time.ZoneId;

/**
 * @author landyl
 * @create 5:19 PM 03/02/2018
 * Timezones are represented by a ZoneId.
 * They can easily be accessed via static factory methods.
 * Timezones define the offsets which are important to convert between instants and local dates and times.
 */
public class TimezonesTest {

    public static void main(String[] args) {
        System.out.println(ZoneId.getAvailableZoneIds());

        long count = ZoneId.getAvailableZoneIds().stream().count();
        System.out.println(count);
        // prints all available timezone ids

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        // ZoneRules[currentStandardOffset=+01:00]
        // ZoneRules[currentStandardOffset=-03:00]
    }

}
