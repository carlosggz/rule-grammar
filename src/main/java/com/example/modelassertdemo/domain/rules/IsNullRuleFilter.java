package com.example.modelassertdemo.domain.rules;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

import com.example.modelassertdemo.domain.models.criteria.EmptyCriteria;
import com.example.modelassertdemo.domain.models.types.BaseMethod;
import com.example.modelassertdemo.domain.models.types.MiscMethod;
import org.opentest4j.AssertionFailedError;
import org.springframework.stereotype.Component;

@Component
public class IsNullRuleFilter implements RuleFilter<EmptyCriteria> {

    @Override
    public boolean matches(EmptyCriteria criteria, String json) {
        try {
            assertJson(json)
                .at(criteria.getFieldName())
                .isNull();
            return true;
        } catch (AssertionFailedError ex) {
            return false;
        }
    }

    @Override
    public BaseMethod getMethod() {
        return MiscMethod.IS_NULL;
    }
}
