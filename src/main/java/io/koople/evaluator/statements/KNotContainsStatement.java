package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.KStatement;
import io.koople.evaluator.KStore;
import io.koople.evaluator.KUser;
import io.koople.evaluator.values.KStringValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KNotContainsStatement extends KStatement<KStringValue> {

    @JsonCreator
    public KNotContainsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KStringValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KStringValue userValue = user.getStringValue(attribute);

        if (userValue != null)
            for (KStringValue value : values)
                if (userValue.contains(value))
                    return false;

        return true;
    }

    public static KNotContainsStatement of(String attribute, Object... values) {
        return new KNotContainsStatement(attribute, new ArrayList<KStringValue>() {{
            Arrays.stream(values).forEach(it -> {
                if (it instanceof String)
                    add(new KStringValue(it.toString()));
            });
        }});
    }
}

