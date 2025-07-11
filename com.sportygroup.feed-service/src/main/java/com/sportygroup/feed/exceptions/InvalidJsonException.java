package com.sportygroup.feed.exceptions;

/**
 * InvalidJsonException.
 */
public class InvalidJsonException extends BaseException {

    public InvalidJsonException(String message) {
        super(message);
    }

    public InvalidJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJsonException(String id, int errorCode, String message, Throwable cause) {
        super(id, errorCode, message, cause);
    }
}
