package com.proeffico.einvoiceService.config;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.repository.cdi.CdiRepositoryExtensionSupport;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Random;

public class TokenConfig {

    public static String generateTimeStamp() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyyHHmmss");
        return format1.format(cal.getTime());

    }

    public static String generateTimeStampForAuthString() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

        String time = format1.format(cal.getTime()) + "+0530";

        return time;
    }

    public static String generateTxnCode() {
        String uniqueData = RandomStringUtils.randomAlphanumeric(4);

        return generateTimeStamp() + uniqueData;
    }

    public static boolean isTimeDifferenceLessThan2Minutes(String targetTime) {
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime targetLocalTime = LocalTime.parse(targetTime, formatter);
        System.out.println(targetLocalTime.toString());
        Duration timeDiff = Duration.between(currentTime, targetLocalTime);
        System.out.println(timeDiff.getSeconds());
        System.out.println(timeDiff.getSeconds() > 120);
        return timeDiff.getSeconds() < 120;

    }
}
