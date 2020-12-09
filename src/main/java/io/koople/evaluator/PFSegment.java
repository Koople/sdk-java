package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFSegmentRule;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;

import java.io.Serializable;
import java.util.List;

public class PFSegment implements Serializable {
    public final String key;
    public final List<PFSegmentRule> rules;

    @JsonCreator
    public PFSegment(@JsonProperty("key") String key, @JsonProperty("rules") List<PFSegmentRule> rules) {
        this.key = key;
        this.rules = rules;
    }

    public boolean evaluate(PFStore store, PFUser user) {
        for (PFSegmentRule rule : rules)
            if (rule.evaluate(store, user))
                return true;

        return false;
    }
}
