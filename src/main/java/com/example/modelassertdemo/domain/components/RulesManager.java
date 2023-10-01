package com.example.modelassertdemo.domain.components;

import com.example.modelassertdemo.domain.models.criteria.Criteria;
import com.example.modelassertdemo.domain.models.types.BaseMethod;
import com.example.modelassertdemo.domain.rules.RuleFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RulesManager {

    private final ObjectMapper objectMapper;
    private final Map<BaseMethod, RuleFilter<? extends Criteria>> rulesMap;

    public RulesManager(ObjectMapper objectMapper, List<RuleFilter<? extends Criteria>> rulesFilter) {
        this.objectMapper = objectMapper;
        this.rulesMap = rulesFilter
            .stream()
            .collect(Collectors.toMap(RuleFilter::getMethod, x -> x));
    }

    public boolean accept(@NonNull Object object, @NonNull List<? extends Criteria> filters) throws JsonProcessingException {
        return filters.isEmpty() || accept(objectMapper.writeValueAsString(object), filters);
    }

    public boolean accept(String json, @NonNull List<? extends Criteria> filters) {
        Assert.isTrue(StringUtils.isNotBlank(json), "Json is not valid");

        for(var f: filters) {
            var result = rulesMap.containsKey(f.getMethod()) &&
                ((RuleFilter<Criteria>)rulesMap.get(f.getMethod())).matches(f, json);

            if (!result) {
                return false;
            }
        }

        return true;
    }
}
