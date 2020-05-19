package com.pataflags.evaluator;

import java.util.List;

public class Statement {
    private final String attribute;
    private final Operator operator;
    private final List<Value<?>> values;

    public Statement(String attribute, Operator operator, List<Value<?>> values) {
        this.attribute = attribute;
        this.operator = operator;
        this.values = values;
    }

    boolean evaluate(User user) {
        Value userValue = user.getValue(attribute);

        if (userValue == null)
            return false;

        for (Value value : values)
            if (operator.evaluate(value, userValue))
                return true;

        return false;
    }
}
