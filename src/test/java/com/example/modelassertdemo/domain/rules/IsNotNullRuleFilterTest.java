package com.example.modelassertdemo.domain.rules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.modelassertdemo.domain.models.criteria.EmptyCriteria;
import com.example.modelassertdemo.domain.models.types.MiscMethod;
import org.junit.jupiter.api.Test;

class IsNotNullRuleFilterTest {

    private final IsNotNullRuleFilter filter = new IsNotNullRuleFilter();

    @Test
    void happyPath() {
        //given
        var json = """
        { "intField": 123 }
        """;
        var givenCriteria = new EmptyCriteria("/intField", MiscMethod.IS_NOT_NULL);

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertTrue(result);
    }

    @Test
    void unhappyPath() {
        //given
        var json = """
        { "intField": null }
        """;
        var givenCriteria = new EmptyCriteria("/intField", MiscMethod.IS_NOT_NULL);

        //when
        var result = filter.matches(givenCriteria, json);

        //then
        assertFalse(result);
    }
}
