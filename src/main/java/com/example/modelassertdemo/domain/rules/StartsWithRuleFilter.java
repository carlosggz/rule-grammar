package com.example.modelassertdemo.domain.rules;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

import com.example.modelassertdemo.domain.models.criteria.StringCriteria;
import com.example.modelassertdemo.domain.models.types.BaseMethod;
import com.example.modelassertdemo.domain.models.types.StringMethod;
import org.opentest4j.AssertionFailedError;
import org.springframework.stereotype.Component;

@Component
public class StartsWithRuleFilter implements RuleFilter<StringCriteria> {

    @Override
    public boolean matches(StringCriteria criteria, String json) {
        try {
            assertJson(json)
                .at(criteria.getFieldName())
                .textStartsWith(criteria.getValue());
            return true;
        } catch (AssertionFailedError ex) {
            return false;
        }
    }

    @Override
    public BaseMethod getMethod() {
        return StringMethod.STARTS_WITH;
    }
}
