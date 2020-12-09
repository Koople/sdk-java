package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFInlineRule;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;

import java.io.Serializable;
import java.util.List;

public class PFRemoteConfigRule implements Serializable {
    public final List<String> identities;
    public final List<PFInlineRule> rules;
    public final String value;

    @JsonCreator
    public PFRemoteConfigRule(@JsonProperty("identities") List<String> identities, @JsonProperty("rules") List<PFInlineRule> rules, @JsonProperty("value") String value) {
        this.identities = identities;
        this.rules = rules;
        this.value = value;
    }

    public boolean evaluate(PFStore store, PFUser user) {
        if (identities.contains(user.getIdentity()))
            return true;

        if (rules.size() > 0)
            for (PFInlineRule rule : rules)
                if (rule.evaluate(store, user))
                    return true;

        return false;
    }
}
