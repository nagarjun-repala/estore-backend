package com.nagarjun.estorebackend;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletResponse;

public class GlobalMethods {

    public static String dateTimeFormatter(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER);
        return formatter.format(localDateTime);
    }

    public static void errorResponseFilter(HttpServletResponse response, String errorMessage, int statusCode) throws IOException{
        response.setStatus(statusCode);
        response.getWriter().write(errorMessage);
        response.getWriter().flush();
    } 
    
}
