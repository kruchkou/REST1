package com.epam.esm.service.exception.impl;

import com.epam.esm.service.exception.ServiceException;

public class TagNotFoundException extends ServiceException {
    public TagNotFoundException() {
        super();
    }

    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public TagNotFoundException(Throwable cause) {
        super(cause);
    }

    public TagNotFoundException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }

}
