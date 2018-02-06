package com.lateroad.buber.exception;

public class BuberSQLException extends Exception {
    public BuberSQLException() {
        super();
    }

    public BuberSQLException(String message) {
        super(message);
    }

    public BuberSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuberSQLException(Throwable cause) {
        super(cause);
    }

    protected BuberSQLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
