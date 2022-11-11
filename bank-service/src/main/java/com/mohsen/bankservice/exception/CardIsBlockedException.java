package com.mohsen.bankservice.exception;

public class CardIsBlockedException extends  RuntimeException{
    public CardIsBlockedException() {
    }

    public CardIsBlockedException(String message) {
        super(message);
    }

    public CardIsBlockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardIsBlockedException(Throwable cause) {
        super(cause);
    }

    public CardIsBlockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
