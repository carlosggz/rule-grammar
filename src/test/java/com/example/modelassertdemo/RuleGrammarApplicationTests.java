package com.example.modelassertdemo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;
import static uk.org.webcompere.modelassert.json.Patterns.GUID_PATTERN;

import com.example.modelassertdemo.domain.components.RulesManager;
import com.example.modelassertdemo.domain.services.ParserService;
import com.example.modelassertdemo.utils.TestUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RuleGrammarApplicationTests {

    @Autowired
    private ParserService parserService;
    @Autowired
    private RulesManager rulesManager;

    private final String givenJson = TestUtils.getResource("demo.json");

    @Test
    void contextLoads() {
    }

    @Test
    void testJsonConditions() {
        //given
        var conditions = """
            field /name starts with "my"
            field /number greater than 10
            field /number between 10 and 20
            field /name starts with "m" and ends with "me" and contains "na"
            field /id is not null
            field /other is null
            """;

        //when
        var filters = parserService.getConditions(conditions);
        var result = rulesManager.accept(givenJson, filters);

        //then
        assertTrue(result);
    }

    @Test
    @SneakyThrows
    void testObjectInstance() {
        //given
        var givenItem = new Item(11, "my name", "id", null);
        var conditions = """
            field /name starts with "my"
            field /number greater than 10
            field /number between 10 and 20
            field /name starts with "m" and ends with "me" and contains "na"
            field /id is not null
            field /other is null
            """;

        //when
        var filters = parserService.getConditions(conditions);
        var result = rulesManager.accept(givenItem, filters);

        //then
        assertTrue(result);
    }

    @Test
    void test() {
        assertNotNull(givenJson);

        var x = assertJson(givenJson)
                .at("/name").isText("my name")
                .at("/boolean").isTrue()
                .at("/array").isArrayContaining("Value2")
                .at("/array").at("/2").isText("Value3")
                .at("/number").isBetween(12, 15)
                .at("/inner/innerObject").isText("inner")
                .at("/id").matches(GUID_PATTERN);

        assertNotNull(x);
    }

    @Data
    @AllArgsConstructor
    private static class Item {
        private int number;
        private String name;
        private String id;
        private String other;
    }
}
