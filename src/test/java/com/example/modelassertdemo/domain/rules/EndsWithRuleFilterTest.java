package com.example.modelassertdemo.domain.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.example.modelassertdemo.domain.models.criteria.StringCriteria;
import com.example.modelassertdemo.domain.models.types.StringMethod;
import org.junit.jupiter.api.Test;

class EndsWithRuleFilterTest {
    private final EndsWithRuleFilter filter = new EndsWithRuleFilter();
    private final String json = """
        { "stringField": "This is a test" }
        """;

    @Test
    void happyPath() {
        //given
        var givenCriteria = new StringCriteria("/stringField", StringMethod.ENDS_WITH, "test");

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertTrue(result);
    }

    @Test
    void unhappyPath() {
        //given
        var givenCriteria = new StringCriteria("/stringField", StringMethod.ENDS_WITH, "is");

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertFalse(result);
    }
}
