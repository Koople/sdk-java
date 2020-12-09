package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFRemoteConfigRule;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;

import java.io.Serializable;
import java.util.List;

public class PFRemoteConfig implements Serializable {
    public final String key;
    public final List<PFRemoteConfigRule> rules;
    public final String defaultValue;

    @JsonCreator
    public PFRemoteConfig(@JsonProperty("key") String key, @JsonProperty("rules") List<PFRemoteConfigRule> rules, @JsonProperty("defaultValue") String defaultValue) {
        this.key = key;
        this.rules = rules;
        this.defaultValue = defaultValue;
    }

    public String evaluate(PFStore store, PFUser user) {
        if (rules.size() > 0)
            for (PFRemoteConfigRule rule : rules)
                if (rule.evaluate(store, user))
                    return rule.value;

        return defaultValue;
    }
}
