package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class KRemoteConfigRule implements Serializable {
    public final List<String> identities;
    public final List<KFInlineRule> rules;
    public final String value;

    @JsonCreator
    public KRemoteConfigRule(@JsonProperty("identities") List<String> identities, @JsonProperty("rules") List<KFInlineRule> rules, @JsonProperty("value") String value) {
        this.identities = identities;
        this.rules = rules;
        this.value = value;
    }

    public boolean evaluate(KStore store, KUser user) {
        if (identities.contains(user.getIdentity()))
            return true;

        if (rules.size() > 0)
            for (KFInlineRule rule : rules)
                if (rule.evaluate(store, user))
                    return true;

        return false;
    }
}
