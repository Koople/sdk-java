package io.koople.sdk.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KStatement;
import io.koople.sdk.evaluator.KStore;
import io.koople.sdk.evaluator.KUser;
import io.koople.sdk.evaluator.values.KStringValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KContainsStatement extends KStatement<KStringValue> {

    @JsonCreator
    public KContainsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KStringValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KStringValue userValue = user.getStringValue(attribute);

        if (userValue != null)
            for (KStringValue value : values)
                if (userValue.contains(value))
                    return true;

        return false;
    }

    public static KContainsStatement of(String attribute, Object... values) {
        return new KContainsStatement(attribute, new ArrayList<KStringValue>() {{
            Arrays.stream(values).forEach(it -> {
                if (it instanceof String)
                    add(new KStringValue(it.toString()));
            });
        }});
    }
}

