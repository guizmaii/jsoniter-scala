package com.jsoniter.input;

import com.jsoniter.JsonIterator;
import com.jsoniter.spi.DecodingMode;
import com.jsoniter.spi.JsonException;

import java.io.IOException;

public class JsoniterJavaParser {
    static {
        JsonIterator.setMode(DecodingMode.DYNAMIC_MODE_AND_MATCH_FIELD_STRICTLY);
    }

    private final static ThreadLocal<JsonIterator> iterators = ThreadLocal.withInitial(JsonIterator::new);

    public static <T> T parse(byte[] input, Class<T> clazz) {
        try {
            JsonIterator iterator = iterators.get();
            iterator.reset(input, 0, input.length);
            return iterator.read(clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }
}
