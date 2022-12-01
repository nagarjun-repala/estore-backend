package com.nagarjun.estorebackend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalMethods {

    public static String dateTimeFormatter(LocalDateTime localDateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER);
        return formatter.format(localDateTime);
    }
    
}
