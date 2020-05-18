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
            return value.contains(userValue);
        }
    },
    greaterThan {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return value.greaterThan(userValue);
        }
    },
    greaterThanOrEquals {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return value.greaterThanOrEquals(userValue);
        }
    },
    lessThan {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return value.lessThan(userValue);
        }
    },
    lessThanOrEquals {
        @Override
        boolean evaluate(Value value, Value userValue) {
            return value.lessThanOrEquals(userValue);
        }
    };

    abstract boolean evaluate(Value value, Value userValue);
}
