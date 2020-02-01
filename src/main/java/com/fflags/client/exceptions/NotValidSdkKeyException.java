package com.fflags.client.exceptions;

public class NotValidSdkKeyException extends RuntimeException {
    public NotValidSdkKeyException() {
        super("Invalid SdkKey");
    }
}
