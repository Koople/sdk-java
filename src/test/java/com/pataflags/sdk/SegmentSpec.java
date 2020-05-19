package com.pataflags.sdk;

import com.pataflags.sdk.values.IntValue;
import com.pataflags.sdk.values.StringValue;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SegmentSpec extends EasyMockSupport {

    Segment segment = new Segment(new ArrayList<Rule>() {{
        add(new Rule(new ArrayList<Statement>(){{
            add(new Statement("country", Operator.equals, new ArrayList<Value<?>>() {{
                add(new StringValue("spain"));
            }}));
            add(new Statement("age", Operator.greaterThanOrEquals, new ArrayList<Value<?>>() {{
                add(new IntValue(18));
            }}));
        }}));
        add(new Rule(new ArrayList<Statement>(){{
            add(new Statement("country", Operator.equals, new ArrayList<Value<?>>() {{
                add(new StringValue("eeuu"));
            }}));
            add(new Statement("age", Operator.greaterThanOrEquals, new ArrayList<Value<?>>() {{
                add(new IntValue(21));
            }}));
        }}));
    }});

    @Test
    public void evaluate_segment_should_true() {
        User user = new User("pedro", new HashMap<String, Value<?>>() {{
            put("country", new StringValue("spain"));
            put("age", new IntValue(18));
        }});

        assertTrue(segment.evaluate(user));
    }

    @Test
    public void evaluate_segment_should_true2() {
        User user = new User("peter", new HashMap<String, Value<?>>() {{
            put("country", new StringValue("eeuu"));
            put("age", new IntValue(21));
        }});

        assertTrue(segment.evaluate(user));
    }

    @Test
    public void evaluate_rule_should_false() {
        User user = new User("pedrito", new HashMap<String, Value<?>>() {{
            put("country", new StringValue("spain"));
            put("age", new IntValue(16));
        }});

        assertFalse(segment.evaluate(user));
    }
}
