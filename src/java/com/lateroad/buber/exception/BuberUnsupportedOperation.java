package com.lateroad.buber.exception;

public class BuberUnsupportedOperation extends RuntimeException  {
    public BuberUnsupportedOperation() {
    }

    public BuberUnsupportedOperation(String message) {
        super(message);
    }

    public BuberUnsupportedOperation(String message, Throwable cause) {
        super(message, cause);
    }

    public BuberUnsupportedOperation(Throwable cause) {
        super(cause);
    }

    public BuberUnsupportedOperation(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
