package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;

public class TagNotFoundException extends RuntimeException {

    protected final static HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public TagNotFoundException() {
        super();
    }

    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNotFoundException(Throwable cause) {
        super(cause);
    }
}
