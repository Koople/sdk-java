package com.pataflags.evaluator;

import java.util.List;

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
