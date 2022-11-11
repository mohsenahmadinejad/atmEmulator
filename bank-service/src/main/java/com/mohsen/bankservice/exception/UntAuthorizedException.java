package com.mohsen.bankservice.exception;

public class UntAuthorizedException extends  RuntimeException{
    public UntAuthorizedException() {
    }

    public UntAuthorizedException(String message) {
        super(message);
    }

    public UntAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UntAuthorizedException(Throwable cause) {
        super(cause);
    }

    public UntAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
