package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No gift certificate with this id")
public class GiftCertificateNotFoundException extends RuntimeException {

    public GiftCertificateNotFoundException() {
        super();
    }

    public GiftCertificateNotFoundException(String message) {
        super(message);
    }

    public GiftCertificateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GiftCertificateNotFoundException(Throwable cause) {
        super(cause);
    }
}