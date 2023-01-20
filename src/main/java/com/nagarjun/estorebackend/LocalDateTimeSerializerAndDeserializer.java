package com.nagarjun.estorebackend;

import java.time.LocalDateTime;
import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeSerializerAndDeserializer implements JsonSerializer <LocalDateTime>, JsonDeserializer <LocalDateTime> {
    
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(GlobalMethods.dateTimeFormatter(localDateTime));
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(),
        GlobalMethods.dateTimeFormatterPattern());
    }

}

