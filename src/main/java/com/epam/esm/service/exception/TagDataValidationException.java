package com.epam.esm.service.exception;

public class TagDataValidationException extends RuntimeException {

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
