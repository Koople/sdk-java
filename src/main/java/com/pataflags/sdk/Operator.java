package com.pataflags.sdk;

enum Operator {
    equals {
        @Override
        public boolean evaluate(Value value, Value userValue) {
            return value.equals(userValue);
        }
    },
    contains {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return userValue.contains(value);
        }
    },
    greaterThan {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return userValue.greaterThan(value);
        }
    },
    greaterThanOrEquals {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return userValue.greaterThanOrEquals(value);
        }
    },
    lessThan {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return userValue.lessThan(value);
        }
    },
    lessThanOrEquals {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return userValue.lessThanOrEquals(value);
        }
    };

    abstract boolean evaluate(Value value, Value userValue);
}
