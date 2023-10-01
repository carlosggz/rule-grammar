package com.example.modelassertdemo.domain.models.criteria;

import com.example.modelassertdemo.domain.models.types.NumberMethod;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class NumberCriteria extends BaseCriteria {
    Number value;
    Number value2;

    public NumberCriteria(
        @NonNull String fieldName,
        @NonNull NumberMethod method,
        @NonNull Number value) {
        this(fieldName, method, value, null);
    }

    public NumberCriteria(
        @NonNull String fieldName,
        @NonNull NumberMethod method,
        @NonNull Number value,
        Number value2) {
        super(fieldName, method);
        this.value = value;
        this.value2 = value2;
    }
}
