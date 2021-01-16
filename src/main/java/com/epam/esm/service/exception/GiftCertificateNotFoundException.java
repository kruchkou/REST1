package com.epam.esm.service.exception;

import org.springframework.http.HttpStatus;

public class GiftCertificateNotFoundException extends RuntimeException {

    protected final static HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }

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