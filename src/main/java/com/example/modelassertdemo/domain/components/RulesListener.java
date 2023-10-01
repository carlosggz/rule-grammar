package com.example.modelassertdemo.domain.components;

import com.example.modelassertdemo.domain.models.criteria.Criteria;
import com.example.modelassertdemo.domain.models.criteria.NumberCriteria;
import com.example.modelassertdemo.domain.models.criteria.StringCriteria;
import com.example.modelassertdemo.domain.models.types.NumberMethod;
import com.example.modelassertdemo.domain.models.types.StringMethod;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RulesListener extends com.example.modelassertdemo.grammar.MyRulesBaseListener {

    @Getter
    private final List<Criteria> filters = new ArrayList<>();
    private String currentFieldName = null;

    @Override public void enterFilter(com.example.modelassertdemo.grammar.MyRulesParser.FilterContext ctx) {
        filters.clear();
        currentFieldName = null;
    }

    @Override
    public void enterLine(com.example.modelassertdemo.grammar.MyRulesParser.LineContext ctx) {
        currentFieldName = ctx.getChild(1).getText();
    }

    @Override
    public void enterString_criteria(com.example.modelassertdemo.grammar.MyRulesParser.String_criteriaContext ctx) {
        filters.add(new StringCriteria(
            currentFieldName,
            StringMethod.fromMessage(ctx.getChild(0).getText()),
            ctx.getChild(1).getText().replace("\"", "")
        ));
    }

    @Override
    public void enterNumber_criteria(com.example.modelassertdemo.grammar.MyRulesParser.Number_criteriaContext ctx) {
        var child = ctx.getChild(0);
        filters.add(new NumberCriteria(
            currentFieldName,
            NumberMethod.fromMessage(child.getChild(0).getText()),
            Integer.parseInt(child.getChild(1).getText()),
            child.getChildCount() == 4 ? Integer.parseInt(child.getChild(3).getText()) : 0
        ));
    }
}
