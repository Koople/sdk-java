package io.koople.sdk.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class KRemoteConfig implements Serializable {
    public final String key;
    public final List<KRemoteConfigRule> rules;
    public final String defaultValue;

    @JsonCreator
    public KRemoteConfig(@JsonProperty("key") String key, @JsonProperty("rules") List<KRemoteConfigRule> rules, @JsonProperty("defaultValue") String defaultValue) {
        this.key = key;
        this.rules = rules;
        this.defaultValue = defaultValue;
    }

    public String evaluate(KStore store, KUser user) {
        if (rules.size() > 0)
            for (KRemoteConfigRule rule : rules)
                if (rule.evaluate(store, user))
                    return rule.value;

        return defaultValue;
    }
}
