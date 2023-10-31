package com.waigore.downdatube.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Pair;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class StringToPairConverter<T> implements Converter<String, Pair<T, T>> {
    @Override
    public Pair convert(String source) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<T>> listType = new TypeReference<List<T>>() {};
        try {
            List<T> list = objectMapper.readValue(source, listType);
            return new Pair(list.get(0), list.get(1));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
