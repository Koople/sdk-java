package com.pataflags.sdk.values;

import com.pataflags.sdk.Value;

public class IntValue extends Value<Integer> {
    public IntValue(Integer value) {
        super(value);
    }

    @Override
    public boolean greaterThan(Value other) {
        if (other instanceof IntValue)
            return value > ((IntValue) other).value;

        return false;
    }

    @Override
    public boolean greaterThanOrEquals(Value other) {
        if (other instanceof IntValue)
            return value >= ((IntValue) other).value;

        return false;
    }

    @Override
    public boolean lessThan(Value other) {
        if (other instanceof IntValue)
            return value < ((IntValue) other).value;

        return false;
    }

    @Override
    public boolean lessThanOrEquals(Value other) {
        if (other instanceof IntValue)
            return value <= ((IntValue) other).value;

        return false;
    }
}
