package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFStatement;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.values.PFBooleanValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PFIsFalsyStatement extends PFStatement<PFBooleanValue> {

    @JsonCreator
    public PFIsFalsyStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<PFBooleanValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(PFStore store, PFUser user) {
        PFBooleanValue userValue = user.getBooleanValue(attribute);

        if (userValue != null)
            return userValue.isFalsy();

        return true;
    }

    public static PFStatement of(String attribute) {
        return new io.koople.evaluator.statements.PFIsFalsyStatement(attribute, new ArrayList<PFBooleanValue>() {{ add(PFBooleanValue.False()); }});
    }
}

