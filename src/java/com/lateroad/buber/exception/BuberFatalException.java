package com.lateroad.buber.exception;

public class BuberFatalException extends RuntimeException {
    public BuberFatalException() {
    }

    public BuberFatalException(String message) {
        super(message);
    }

    public BuberFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuberFatalException(Throwable cause) {
        super(cause);
    }

    public BuberFatalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
