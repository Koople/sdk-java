package io.koople.sdk.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KStatement;
import io.koople.sdk.evaluator.KStore;
import io.koople.sdk.evaluator.KUser;
import io.koople.sdk.evaluator.KValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class KExistsStatement extends KStatement<KValue> {

    @JsonCreator
    public KExistsStatement(@JsonProperty("attribute") @NotNull String attribute) {
        super(attribute, new ArrayList<>());
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KValue userValue = user.getValue(attribute);

        return userValue != null && userValue.asString() != null && userValue.asString().isNonEmpty();
    }

    public static KExistsStatement of(String attribute) {
        return new KExistsStatement(attribute);
    }
}

