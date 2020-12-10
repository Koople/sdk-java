package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.KStatement;
import io.koople.evaluator.KStore;
import io.koople.evaluator.KUser;
import io.koople.evaluator.values.KNumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KGreaterThanStatement extends KStatement<KNumberValue> {

    @JsonCreator
    public KGreaterThanStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KNumberValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KNumberValue userValue = user.getNumberValue(attribute);

        if (userValue != null)
            return userValue.greaterThan(values.get(0));

        return false;
    }

    public static KGreaterThanStatement of(String attribute, Integer value) {
        return new KGreaterThanStatement(attribute, new ArrayList<KNumberValue>() {{ add(new KNumberValue(value)); }});
    }
}

