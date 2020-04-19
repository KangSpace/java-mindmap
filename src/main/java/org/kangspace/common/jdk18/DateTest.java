package org.kangspace.common.jdk18;

import java.time.*;

/**
 * 2019/12/12 11:19
 *
 * @author kango2gler@gmail.com
 */
public class DateTest {
    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        System.out.println(clock);
        Instant instant =clock.instant();
        System.out.println(instant.getNano());
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        Duration.ofDays(100);
    }
}
