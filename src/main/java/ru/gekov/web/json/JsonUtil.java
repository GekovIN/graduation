package ru.gekov.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationConfig;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ru.gekov.web.json.JacksonObjectMapper.getMapper;

public class JsonUtil {

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = getMapper().readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public static <T> String writeAdditionPropsWithCustomView(T obj, String addName, Object addValue, Class clazz) {
        return writeAdditionPropsWithCustomView(obj, Map.of(addName, addValue), clazz);
    }

    public static <T> String writeAdditionPropsWithCustomView(T obj, Map<String, Object> addProps, Class clazz) {
        ObjectMapper mapper = getMapper();
        mapper.setConfig(mapper.getSerializationConfig().withView(clazz));

        Map<String, Object> map = mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
        map.putAll(addProps);
        return writeValue(map);
    }
}
