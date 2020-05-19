package com.pataflags.evaluator;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeatureSpec extends EasyMockSupport {

    Segment segment = new Segment(new ArrayList<Rule>() {{
        add(new Rule(new ArrayList<Statement>(){{
            add(new Statement("country", Operator.equals, new ArrayList<Value<?>>() {{
                add(new StringValue("spain"));
            }}));
            add(new Statement("age", Operator.greaterThanOrEquals, new ArrayList<Value<?>>() {{
                add(new IntValue(18));
            }}));
        }}));
    }});

    User pedro = new User("pedrito", new HashMap<String, Value<?>>() {{
        put("country", new StringValue("spain"));
        put("age", new IntValue(50));
    }});

    User pedrito = new User("pedrito", new HashMap<String, Value<?>>() {{
        put("country", new StringValue("spain"));
        put("age", new IntValue(16));
    }});

    @Test
    public void evaluate_enabledForAll_feature_should_true() {
        Feature feature = new Feature("enabledForAll", FeatureTargeting.all);

        assertTrue(feature.evaluate(pedrito));
    }

    @Test
    public void evaluate_disabled_feature_should_false() {
        Feature feature = new Feature("disabled", FeatureTargeting.disabled);

        assertFalse(feature.evaluate(pedrito));
    }

    @Test
    public void evaluate_enabledForHimself_feature_should_true() {
        Feature feature = new Feature("enabledForHimself", FeatureTargeting.segment, new ArrayList<String>() {{ add("pedrito"); }}, new ArrayList<Segment>());

        assertTrue(feature.evaluate(pedrito));
    }

    @Test
    public void evaluate_nonEnabledForHimself_feature_should_false() {
        Feature feature = new Feature("enabledForHimself", FeatureTargeting.segment, new ArrayList<String>(), new ArrayList<Segment>());

        assertFalse(feature.evaluate(pedrito));
    }

    @Test
    public void evaluate_segmented_feature_should_true() {
        Feature feature = new Feature("segmented", FeatureTargeting.segment, new ArrayList<String>(), new ArrayList<Segment>() {{ add(segment); }});

        assertTrue(feature.evaluate(pedro));
    }

    @Test
    public void evaluate_segmented_feature_should_false() {
        Feature feature = new Feature("segmented", FeatureTargeting.segment, new ArrayList<String>(), new ArrayList<Segment>() {{ add(segment); }});

        assertFalse(feature.evaluate(pedrito));
    }
}
