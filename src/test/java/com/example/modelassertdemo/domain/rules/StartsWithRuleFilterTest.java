package com.example.modelassertdemo.domain.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.example.modelassertdemo.domain.models.criteria.StringCriteria;
import com.example.modelassertdemo.domain.models.types.StringMethod;
import org.junit.jupiter.api.Test;

class StartsWithRuleFilterTest {
    private final StartsWithRuleFilter filter = new StartsWithRuleFilter();
    private final String json = """
        { "stringField": "This is a test" }
        """;

    @Test
    void happyPath() {
        //given
        var givenCriteria = new StringCriteria("/stringField", StringMethod.STARTS_WITH, "This");

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertTrue(result);
    }

    @Test
    void unhappyPath() {
        //given
        var givenCriteria = new StringCriteria("/stringField", StringMethod.STARTS_WITH, "is");

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertFalse(result);
    }
}
