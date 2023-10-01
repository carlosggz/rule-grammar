package com.example.modelassertdemo.domain.rules;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

import com.example.modelassertdemo.domain.models.criteria.NumberCriteria;
import com.example.modelassertdemo.domain.models.types.BaseMethod;
import com.example.modelassertdemo.domain.models.types.NumberMethod;
import org.opentest4j.AssertionFailedError;
import org.springframework.stereotype.Component;

@Component
public class EqualsRuleFilter implements RuleFilter<NumberCriteria> {

    @Override
    public boolean matches(NumberCriteria criteria, String json) {
        try {
            assertJson(json)
                .at(criteria.getFieldName())
                .isNumberEqualTo(criteria.getValue());
            return true;
        } catch (AssertionFailedError ex) {
            return false;
        }
    }

    @Override
    public BaseMethod getMethod() {
        return NumberMethod.EQUALS_TO;
    }
}
