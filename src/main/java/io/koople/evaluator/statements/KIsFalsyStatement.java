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

public class KIsFalsyStatement extends KStatement<KBooleanValue> {

    @JsonCreator
    public KIsFalsyStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KBooleanValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KBooleanValue userValue = user.getBooleanValue(attribute);

        if (userValue != null)
            return userValue.isFalsy();

        return true;
    }

    public static KStatement of(String attribute) {
        return new KIsFalsyStatement(attribute, new ArrayList<KBooleanValue>() {{ add(KBooleanValue.False()); }});
    }
}

