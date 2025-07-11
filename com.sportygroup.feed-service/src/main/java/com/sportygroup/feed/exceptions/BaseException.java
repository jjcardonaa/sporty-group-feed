package com.sportygroup.feed.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Base Exception for sporty group feed service.
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseException extends RuntimeException {

    protected String id;
    protected int errorCode;

    public BaseException(String message) {
        this(null, 0, message, null);
    }

    public BaseException(String message, Throwable cause) {
        this(null, 0, message, cause);
    }

    public BaseException(String id, int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.id = id == null ? UUID.randomUUID().toString() : id;
        this.errorCode = errorCode;
    }
}
