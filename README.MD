## rule-grammar

Object evaluation system using a system of rules based on the [Model Assert](https://github.com/webcompere/model-assert) library and expressed through a grammar managed through [Antlr4](https://github.com/antlr/antlr4).

### How to use it

Having an object like:

```
{
    "name": "my name",
    "number": 12,
    "id": "df0c4dc5-fc6e-4f96-8670-e032c887ef32",
    "other": null
}
```

We  can have some rules like:

```
field /name starts with "my"
field /number greater than 10
field /number between 10 and 20
field /name starts with "m" and ends with "me" and contains "na"
field /id is not null
field /other is null
```

To parse the rules, use the [Parser Service](src/main/java/com/example/modelassertdemo/domain/services/ParserService.java):

```
List<Criteria> filters = parserService.getConditions(conditions);
```

To verify the conditions over an object, use the [Rules Manager](src/main/java/com/example/modelassertdemo/domain/components/RulesManager.java):

```
boolean isValid = rulesManager.accept(myJson, filters);
```

See a complete example on the [tests](src/test/java/com/example/modelassertdemo/ModelAssertDemoApplicationTests.java)

TODO:
- Implement or operator and composite rules
- Add more rules (regexp, in)