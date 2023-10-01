package com.example.modelassertdemo.domain.rules;

import com.example.modelassertdemo.domain.models.criteria.Criteria;
import com.example.modelassertdemo.domain.models.types.BaseMethod;

public interface RuleFilter<T extends Criteria> {
    boolean matches(T criteria, String json);
    BaseMethod getMethod();
}
