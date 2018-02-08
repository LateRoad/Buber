package com.lateroad.buber.exception;

public class BuberUnsupportedOperationException extends RuntimeException  {
    public BuberUnsupportedOperationException() {
    }

    public BuberUnsupportedOperationException(String message) {
        super(message);
    }

    public BuberUnsupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuberUnsupportedOperationException(Throwable cause) {
        super(cause);
    }

    public BuberUnsupportedOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
