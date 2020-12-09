package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFStatement;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.PFValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PFNotExistsStatement extends PFStatement<PFValue> {

    @JsonCreator
    public PFNotExistsStatement(@JsonProperty("attribute") @NotNull String attribute) {
        super(attribute, new ArrayList<>());
    }

    @Override
    public boolean evaluate(PFStore store, PFUser user) {
        PFValue userValue = user.getValue(attribute);

        return userValue == null || (userValue.asString() != null && userValue.asString().isEmpty());
    }

    public static io.koople.evaluator.statements.PFNotExistsStatement of(String attribute) {
        return new io.koople.evaluator.statements.PFNotExistsStatement(attribute);
    }
}

