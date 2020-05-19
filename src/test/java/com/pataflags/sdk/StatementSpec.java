package com.pataflags.sdk;

import com.pataflags.sdk.values.IntValue;
import com.pataflags.sdk.values.StringValue;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StatementSpec extends EasyMockSupport {

    Statement statement = new Statement("country", Operator.equals, new ArrayList<Value<?>>() {{
        add(new StringValue("spain"));
    }});

    @Test
    public void evaluate_statement_should_true() {
        User user = new User("pedro", new HashMap<String, Value<?>>() {{
            put("country", new StringValue("spain"));
        }});

        assertTrue(statement.evaluate(user));
    }

    @Test
    public void evaluate_statement_with_unknown_value_should_false() {
        User user = new User("peter", new HashMap<String, Value<?>>() {{
            put("country", new StringValue("eeuu"));
        }});

        assertFalse(statement.evaluate(user));
    }

    @Test
    public void evaluate_statement_with_unknown_attribute_should_false() {
        User user = new User("pedrito", new HashMap<String, Value<?>>() {{
            put("age", new IntValue(18));
        }});

        assertFalse(statement.evaluate(user));
    }
}
