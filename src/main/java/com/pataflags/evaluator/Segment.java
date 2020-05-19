package com.pataflags.evaluator;

import java.util.List;

public class Segment {
    private final List<Rule> rules;

    public Segment(List<Rule> rules) {
        this.rules = rules;
    }

    boolean evaluate(User user) {
        for (Rule rule : rules)
            if (rule.evaluate(user))
                return true;

        return false;
    }
}
