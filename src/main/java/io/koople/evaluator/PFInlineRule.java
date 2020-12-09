package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFStatement;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;

import java.io.Serializable;
import java.util.List;

public class PFInlineRule implements Serializable {
    public final Integer order;
    public final List<PFStatement> statements;

    @JsonCreator
    public PFInlineRule(@JsonProperty("order") Integer order, @JsonProperty("statements") List<PFStatement> statements) {
        this.order = order;
        this.statements = statements;
    }

    public boolean evaluate(PFStore store, PFUser user) {
        for (PFStatement statement : statements)
            if (!statement.evaluate(store, user))
                return false;

        return true;
    }
}
