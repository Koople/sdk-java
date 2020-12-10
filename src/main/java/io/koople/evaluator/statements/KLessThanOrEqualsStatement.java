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

public class KLessThanOrEqualsStatement extends KStatement<KNumberValue> {

    @JsonCreator
    public KLessThanOrEqualsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KNumberValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KNumberValue userValue = user.getNumberValue(attribute);

        if (userValue != null)
            return userValue.lessThanOrEquals(values.get(0));

        return false;
    }

    public static KLessThanOrEqualsStatement of(String attribute, Integer value) {
        return new KLessThanOrEqualsStatement(attribute, new ArrayList<KNumberValue>() {{ add(new KNumberValue(value)); }});
    }
}

