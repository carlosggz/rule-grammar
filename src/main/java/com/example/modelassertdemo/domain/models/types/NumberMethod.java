package com.example.modelassertdemo.domain.models.types;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NumberMethod implements BaseMethod {
    EQUALS_TO("equals to"),
    GREATER_THAN("greater than"),
    LESS_THAN("less than"),

    BETWEEN("between");

    private final String message;

    public static NumberMethod fromMessage(String message) {
        return Arrays.stream(NumberMethod.values())
            .filter(x -> x.message.equals(message))
            .findFirst()
            .orElseThrow();
    }
}
