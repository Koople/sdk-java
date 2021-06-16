package io.koople.sdk.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KStatement;
import io.koople.sdk.evaluator.KStore;
import io.koople.sdk.evaluator.KUser;
import io.koople.sdk.evaluator.KValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class KNotExistsStatement extends KStatement<KValue> {

    @JsonCreator
    public KNotExistsStatement(@JsonProperty("attribute") @NotNull String attribute) {
        super(attribute, new ArrayList<>());
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        KValue userValue = user.getValue(attribute);

        return userValue == null || (userValue.asString() != null && userValue.asString().isEmpty());
    }

    public static KNotExistsStatement of(String attribute) {
        return new KNotExistsStatement(attribute);
    }
}

