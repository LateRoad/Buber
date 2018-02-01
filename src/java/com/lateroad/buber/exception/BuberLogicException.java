package com.lateroad.buber.exception;

public class BuberLogicException extends Exception {
    public BuberLogicException() {
        super();
    }

    public BuberLogicException(String message) {
        super(message);
    }

    public BuberLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuberLogicException(Throwable cause) {
        super(cause);
    }

    protected BuberLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
