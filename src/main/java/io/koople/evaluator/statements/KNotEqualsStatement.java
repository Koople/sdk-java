package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.KStatement;
import io.koople.evaluator.KStore;
import io.koople.evaluator.KUser;
import io.koople.evaluator.KValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class KNotEqualsStatement extends KStatement<KValue> {

    @JsonCreator
    public KNotEqualsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<KValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KValue userValue = user.getValue(attribute);

        if (userValue != null)
            for (KValue value : values)
                if (userValue.equals(value))
                    return false;

        return true;
    }

    public static KNotEqualsStatement of(String attribute, Object... values) {
        return new KNotEqualsStatement(attribute, new ArrayList<KValue>() {{
            Arrays.stream(values).filter(Objects::nonNull).forEach(it -> add(KValue.create(it)));
        }});
    }
}

