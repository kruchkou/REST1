package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GiftCertificateDataValidationException extends RuntimeException {

    public GiftCertificateDataValidationException() {
        super();
    }

    public GiftCertificateDataValidationException(String message) {
        super(message);
    }

    public GiftCertificateDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GiftCertificateDataValidationException(Throwable cause) {
        super(cause);
    }
}
