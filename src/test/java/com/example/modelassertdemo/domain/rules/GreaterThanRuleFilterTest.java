package com.example.modelassertdemo.domain.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.example.modelassertdemo.domain.models.criteria.NumberCriteria;
import com.example.modelassertdemo.domain.models.types.NumberMethod;
import org.junit.jupiter.api.Test;

class GreaterThanRuleFilterTest {
    private final GreaterThanRuleFilter filter = new GreaterThanRuleFilter();
    private final String json = """
        { "intField": 12 }
        """;

    @Test
    void happyPath() {
        //given
        var givenCriteria = new NumberCriteria("/intField", NumberMethod.EQUALS_TO, 10);

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertTrue(result);
    }

    @Test
    void unhappyPath() {
        //given
        var givenCriteria = new NumberCriteria("/intField", NumberMethod.BETWEEN, 20);

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertFalse(result);
    }
}
