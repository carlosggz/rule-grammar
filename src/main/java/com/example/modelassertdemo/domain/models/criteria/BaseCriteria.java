package com.example.modelassertdemo.domain.models.criteria;

import com.example.modelassertdemo.domain.models.types.BaseMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public abstract class BaseCriteria implements Criteria {
    String fieldName;
    BaseMethod method;
}
