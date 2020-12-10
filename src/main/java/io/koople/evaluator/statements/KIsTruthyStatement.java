package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.KStatement;
import io.koople.evaluator.KStore;
import io.koople.evaluator.KUser;
import io.koople.evaluator.values.KBooleanValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KIsTruthyStatement extends KStatement<KBooleanValue> {

    @JsonCreator
    public KIsTruthyStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KBooleanValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KBooleanValue userValue = user.getBooleanValue(attribute);

        if (userValue != null)
            return userValue.isTruthy();

        return false;
    }

    public static KStatement of(String attribute) {
        return new KIsTruthyStatement(attribute, new ArrayList<KBooleanValue>() {{ add(KBooleanValue.True()); }});
    }
}

