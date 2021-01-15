package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Tag data validation failed")
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
