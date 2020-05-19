package com.pataflags.evaluator;

public abstract class Value<T> {
    protected final T value;

    public Value(T value) {
        this.value = value;
    }

    public boolean equals(Value other) {
        if (getClass().equals(other.getClass()))
            return value.equals(other.value);

        return false;
    }

    public boolean contains(Value other) {
        return false;
    }

    public boolean greaterThan(Value other) {
        return false;
    }

    public boolean greaterThanOrEquals(Value other) {
        return false;
    }

    public boolean lessThan(Value other) {
        return false;
    }

    public boolean lessThanOrEquals(Value other) {
        return false;
    }
}
