package com.pataflags.sdk.values;

import com.pataflags.sdk.Value;

public class StringValue extends Value<String> {
    public StringValue(String value) {
        super(value);
    }

    @Override
    public boolean contains(Value other) {
        if (other instanceof StringValue)
            return value.contains(((StringValue) other).value);

        return false;
    }
}
