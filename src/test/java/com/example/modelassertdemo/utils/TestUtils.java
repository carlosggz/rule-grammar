package com.example.modelassertdemo.utils;

import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {
    @SneakyThrows
    public String getResource(String fileName) {
        try (var is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
