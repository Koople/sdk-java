package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class KSegment implements Serializable {
    public final String key;
    public final List<KSegmentRule> rules;

    @JsonCreator
    public KSegment(@JsonProperty("key") String key, @JsonProperty("rules") List<KSegmentRule> rules) {
        this.key = key;
        this.rules = rules;
    }

    public boolean evaluate(KStore store, KUser user) {
        for (KSegmentRule rule : rules)
            if (rule.evaluate(store, user))
                return true;

        return false;
    }
}
