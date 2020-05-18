package com.pataflags.sdk;

import java.util.List;
import java.util.Map;

public class Rule {
    private final List<Statement> statements;

    public Rule(List<Statement> statements) {
        this.statements = statements;
    }

    public boolean evaluate(User user) {
        for (Statement statement : statements)
            if (!statement.evaluate(user))
                return false;

        return true;
    }
}
