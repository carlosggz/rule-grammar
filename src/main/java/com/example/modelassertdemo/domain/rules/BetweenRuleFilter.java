package com.example.modelassertdemo.domain.rules;

import com.example.modelassertdemo.domain.models.criteria.NumberCriteria;
import com.example.modelassertdemo.domain.models.types.BaseMethod;
import com.example.modelassertdemo.domain.models.types.NumberMethod;
import org.opentest4j.AssertionFailedError;
import org.springframework.stereotype.Component;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@Component
public class BetweenRuleFilter implements RuleFilter<NumberCriteria> {

    @Override
    public boolean matches(NumberCriteria criteria, String json) {
        try {
            assertJson(json)
                .at(criteria.getFieldName())
                .isBetween(criteria.getValue(), criteria.getValue2());
            return true;
        } catch (AssertionFailedError ex) {
            return false;
        }
    }

    @Override
    public BaseMethod getMethod() {
        return NumberMethod.BETWEEN;
    }
}
