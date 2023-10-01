package com.example.modelassertdemo.domain.models.criteria;

import com.example.modelassertdemo.domain.models.types.StringMethod;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class StringCriteria extends BaseCriteria {
    String value;

    public StringCriteria(
        @NonNull String fieldName,
        @NonNull StringMethod method,
        @NonNull String value) {
        super(fieldName, method);
        this.value = value;
    }
}

