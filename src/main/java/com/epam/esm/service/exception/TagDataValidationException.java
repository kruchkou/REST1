package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;

public class TagDataValidationException extends RuntimeException {

    private final static HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }

    public TagDataValidationException() {
        super();
    }

    public TagDataValidationException(String message) {
        super(message);
    }

    public TagDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagDataValidationException(Throwable cause) {
        super(cause);
    }
}
