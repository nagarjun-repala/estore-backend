package com.nagarjun.estorebackend;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.nagarjun.estorebackend.exception.ErrorResponse;

public class GlobalMethods {

    public static String dateTimeFormatter(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER);
        return formatter.format(localDateTime);
    }

    public static DateTimeFormatter dateTimeFormatterPattern(){
        return DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER);
    }

    public static void responseHandler(HttpServletResponse response, String errorMessage, int statusCode) throws IOException{

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializerAndDeserializer());
        response.setContentType(Constants.CONTENT_TYPE);
        response.setCharacterEncoding(Constants.CHAR_ENCOCDE);
        response.setStatus(statusCode);
        response.getWriter().print(gsonBuilder.setPrettyPrinting().create().toJson(new ErrorResponse(Arrays.asList(errorMessage))));
        response.getWriter().flush();
    } 
    
}
