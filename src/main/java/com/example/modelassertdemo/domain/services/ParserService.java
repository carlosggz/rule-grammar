package com.example.modelassertdemo.domain.services;

import com.example.modelassertdemo.domain.components.RulesListener;
import com.example.modelassertdemo.domain.models.criteria.Criteria;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ParserService {
    private final ParseTreeWalker walker = new ParseTreeWalker();

    public List<Criteria> getConditions(String text) {
        if (StringUtils.isBlank(text)) {
            return List.of();
        }

        // Get the parser
        final var parser = getParser(text);

        //Return the results
        return walkThrough(parser);
    }

    private com.example.modelassertdemo.grammar.MyRulesParser getParser(String text) {
        // Create the char stream
        final var ch = CharStreams.fromString(
            text + (StringUtils.endsWith(text, StringUtils.LF) ? StringUtils.EMPTY : StringUtils.LF));

        // Create the lexer
        final var lexer = new com.example.modelassertdemo.grammar.MyRulesLexer(ch);

        // Create a buffer of tokens
        final var tokens = new CommonTokenStream(lexer);

        // Create a parser
        return new com.example.modelassertdemo.grammar.MyRulesParser(tokens);
    }

    private List<Criteria> walkThrough(com.example.modelassertdemo.grammar.MyRulesParser parser) {
        //Create the listener
        var listener = new RulesListener();

        // Process the rules
        walker.walk(listener, parser.filter());

        //Return the results
        return listener.getFilters();
    }
}
