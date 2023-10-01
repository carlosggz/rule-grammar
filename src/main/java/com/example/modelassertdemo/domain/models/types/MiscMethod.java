package com.example.modelassertdemo.domain.models.types;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MiscMethod implements BaseMethod {
    IS_NULL("is null"),
    IS_NOT_NULL("is not null");

    private final String message;

    public static MiscMethod fromMessage(String message) {
        return Arrays.stream(MiscMethod.values())
            .filter(x -> x.message.equals(message))
            .findFirst()
            .orElseThrow();
    }
}
