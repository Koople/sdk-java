package io.koople.sdk.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KStatement;
import io.koople.sdk.evaluator.KStore;
import io.koople.sdk.evaluator.KUser;
import io.koople.sdk.evaluator.KValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class KEqualsStatement extends KStatement<KValue> {

    @JsonCreator
    public KEqualsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KValue userValue = user.getValue(attribute);

        if (userValue != null)
            for (KValue value : values)
                if (userValue.equals(value))
                    return true;

        return false;
    }

    public static KEqualsStatement of(String attribute, Object... values) {
        return new KEqualsStatement(attribute, new ArrayList<KValue>() {{
            Arrays.stream(values).filter(Objects::nonNull).forEach(it -> add(KValue.create(it)));
        }});
    }
}

