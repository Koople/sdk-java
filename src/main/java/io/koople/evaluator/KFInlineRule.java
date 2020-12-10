package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class KFInlineRule implements Serializable {
    public final Integer order;
    public final List<KStatement> statements;

    @JsonCreator
    public KFInlineRule(@JsonProperty("order") Integer order, @JsonProperty("statements") List<KStatement> statements) {
        this.order = order;
        this.statements = statements;
    }

    public boolean evaluate(KStore store, KUser user) {
        for (KStatement statement : statements)
            if (!statement.evaluate(store, user))
                return false;

        return true;
    }
}
