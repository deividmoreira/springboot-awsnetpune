package br.com.itau.neptunepoc.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class JacksonUtils {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper =
                new ObjectMapper().registerModule(new SimpleModule()).registerModule(new JavaTimeModule())
                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                        .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                        .setSerializationInclusion(Include.NON_NULL);
    }

    private JacksonUtils() {

    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static <T> T loadObject(String resourceName, Class<T> clazz) {
        try {
            var file = new File(resourceName);
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao carregar arquivo Json: ", e);
        }
    }

    public static String fromObject(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Objeto nao serializavel para json", e);
        }
    }
}