package com.pataflags.evaluator;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public class EvaluatorSpec extends EasyMockSupport {

    Segment spainAdults = new Segment(new ArrayList<Rule>() {{
        add(new Rule(new ArrayList<Statement>(){{
            add(new Statement("country", Operator.equals, new ArrayList<Value<?>>() {{
                add(new StringValue("spain"));
            }}));
            add(new Statement("age", Operator.greaterThanOrEquals, new ArrayList<Value<?>>() {{
                add(new IntValue(18));
            }}));
        }}));
    }});

    Segment eeuuAdults = new Segment(new ArrayList<Rule>() {{
        add(new Rule(new ArrayList<Statement>(){{
            add(new Statement("country", Operator.equals, new ArrayList<Value<?>>() {{
                add(new StringValue("eeuu"));
            }}));
            add(new Statement("age", Operator.greaterThanOrEquals, new ArrayList<Value<?>>() {{
                add(new IntValue(21));
            }}));
        }}));
    }});

    User user = new User("pedrito", new HashMap<String, Value<?>>() {{
        put("country", new StringValue("spain"));
        put("age", new IntValue(18));
    }});

    private List<Feature> features = new ArrayList<Feature>() {{
        add(new Feature("disabledForAll", FeatureTargeting.disabled));
        add(new Feature("enabledForHimself", FeatureTargeting.segment, new ArrayList<String>() {{ add("pedrito"); }}, new ArrayList<Segment>()));
        add(new Feature("enabledForOther", FeatureTargeting.segment, new ArrayList<String>() {{ add("paco"); }}, new ArrayList<Segment>()));
        add(new Feature("enabledForSpainAdults", FeatureTargeting.segment, new ArrayList<String>(), new ArrayList<Segment>() {{ add(spainAdults); }}));
        add(new Feature("enabledForEeuuAdults", FeatureTargeting.segment, new ArrayList<String>(), new ArrayList<Segment>() {{ add(eeuuAdults); }}));
        add(new Feature("enabledForAll", FeatureTargeting.all));
    }};

    @Test
    public void should_evaluate() throws IOException {
        Map<String, Boolean> evaluation = new Evaluator(features).evaluate(user);

        assertEquals(evaluation, new HashMap<String, Boolean>() {{
            put("disabledForAll", false);
            put("enabledForHimself", true);
            put("enabledForOther", false);
            put("enabledForSpainAdults", true);
            put("enabledForEeuuAdults", false);
            put("enabledForAll", true);
        }});
    }
}
