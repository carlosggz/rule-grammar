package com.example.modelassertdemo.domain.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.example.modelassertdemo.domain.models.criteria.StringCriteria;
import com.example.modelassertdemo.domain.models.types.StringMethod;
import org.junit.jupiter.api.Test;

class ContainsRuleFilterTest {
    private final ContainsRuleFilter filter = new ContainsRuleFilter();
    private final String json = """
        { "stringField": "This is a test" }
        """;

    @Test
    void happyPath() {
        //given
        var givenCriteria = new StringCriteria("/stringField", StringMethod.CONTAINS, "is");

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertTrue(result);
    }

    @Test
    void unhappyPath() {
        //given
        var givenCriteria = new StringCriteria("/stringField", StringMethod.CONTAINS, "hello");

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertFalse(result);
    }
}
