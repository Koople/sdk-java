package com.pataflags.sdk.exceptions;

public class NotValidSdkKeyException extends RuntimeException {
    public NotValidSdkKeyException() {
        super("Invalid SdkKey");
    }
}
