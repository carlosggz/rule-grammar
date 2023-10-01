package com.example.modelassertdemo.domain.models.types;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StringMethod implements BaseMethod {
    STARTS_WITH("starts with"),
    ENDS_WITH("ends with"),
    CONTAINS("contains");

    private final String message;

    public static StringMethod fromMessage(String message) {
        return Arrays.stream(StringMethod.values())
            .filter(x -> x.message.equals(message))
            .findFirst()
            .orElseThrow();
    }
}
