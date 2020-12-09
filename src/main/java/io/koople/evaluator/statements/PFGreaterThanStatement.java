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

public class PFGreaterThanStatement extends PFStatement<PFNumberValue> {

    @JsonCreator
    public PFGreaterThanStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<PFNumberValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(PFStore store, PFUser user) {
        PFNumberValue userValue = user.getNumberValue(attribute);

        if (userValue != null)
            return userValue.greaterThan(values.get(0));

        return false;
    }

    public static io.koople.evaluator.statements.PFGreaterThanStatement of(String attribute, Integer value) {
        return new io.koople.evaluator.statements.PFGreaterThanStatement(attribute, new ArrayList<PFNumberValue>() {{ add(new PFNumberValue(value)); }});
    }
}

