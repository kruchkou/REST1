package com.epam.esm.service.exception;

public class GiftCertificateDataValidationException extends RuntimeException {

    public GiftCertificateDataValidationException() {
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
