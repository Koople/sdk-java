package com.pataflags.evaluator;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RuleSpec extends EasyMockSupport {

    Rule rule = new Rule(new ArrayList<Statement>(){{
        add(new Statement("country", Operator.equals, new ArrayList<Value<?>>() {{
            add(new StringValue("spain"));
        }}));
        add(new Statement("age", Operator.greaterThanOrEquals, new ArrayList<Value<?>>() {{
            add(new IntValue(18));
        }}));
    }});

    @Test
    public void evaluate_rule_should_true() {
        User user = new User("pedro", new HashMap<String, Value<?>>() {{
            put("country", new StringValue("spain"));
            put("age", new IntValue(50));
        }});

        assertTrue(rule.evaluate(user));
    }

    @Test
    public void evaluate_rule_should_false() {
        User user = new User("pedrito", new HashMap<String, Value<?>>() {{
            put("country", new StringValue("spain"));
            put("age", new IntValue(16));
        }});

        assertFalse(rule.evaluate(user));
    }
}
