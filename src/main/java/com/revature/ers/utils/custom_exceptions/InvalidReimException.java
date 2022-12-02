package com.revature.ers.utils.custom_exceptions;

public class InvalidReimException extends RuntimeException{
    public InvalidReimException() {
        super();
    }

    public InvalidReimException(String message) {
        super(message);
    }

    public InvalidReimException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReimException(Throwable cause) {
        super(cause);
    }

    protected InvalidReimException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
