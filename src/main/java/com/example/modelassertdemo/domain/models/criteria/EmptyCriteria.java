package com.example.modelassertdemo.domain.models.criteria;

import com.example.modelassertdemo.domain.models.types.MiscMethod;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class EmptyCriteria extends BaseCriteria {

    public EmptyCriteria(
        @NonNull String fieldName,
        @NonNull MiscMethod method) {
        super(fieldName, method);
    }
}
