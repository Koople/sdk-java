package io.koople.sdk.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KStatement;
import io.koople.sdk.evaluator.KStore;
import io.koople.sdk.evaluator.KUser;
import io.koople.sdk.evaluator.values.KNumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KLessThanStatement extends KStatement<KNumberValue> {

    @JsonCreator
    public KLessThanStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KNumberValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KNumberValue userValue = user.getNumberValue(attribute);

        if (userValue != null)
            return userValue.lessThan(values.get(0));

        return false;
    }

    public static KLessThanStatement of(String attribute, Integer value) {
        return new KLessThanStatement(attribute, new ArrayList<KNumberValue>() {{ add(new KNumberValue(value)); }});
    }
}

