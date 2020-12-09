package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFStatement;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.values.PFNumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PFGreaterThanOrEqualsStatement extends PFStatement<PFNumberValue> {

    @JsonCreator
    public PFGreaterThanOrEqualsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<PFNumberValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(PFStore store, PFUser user) {
        PFNumberValue userValue = user.getNumberValue(attribute);

        if (userValue != null)
            return userValue.greaterThanOrEquals(values.get(0));

        return false;
    }

    public static io.koople.evaluator.statements.PFGreaterThanOrEqualsStatement of(String attribute, Integer value) {
        return new io.koople.evaluator.statements.PFGreaterThanOrEqualsStatement(attribute, new ArrayList<PFNumberValue>() {{ add(new PFNumberValue(value)); }});
    }
}

