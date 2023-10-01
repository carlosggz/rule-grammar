package com.example.modelassertdemo.domain.models.criteria;

import com.example.modelassertdemo.domain.models.types.BaseMethod;

public interface Criteria {
    String getFieldName();
    BaseMethod getMethod();
}
