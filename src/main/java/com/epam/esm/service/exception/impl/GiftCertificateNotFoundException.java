package com.epam.esm.service.exception.impl;

import com.epam.esm.service.exception.ServiceException;

public class GiftCertificateNotFoundException extends ServiceException {
    public GiftCertificateNotFoundException() {
        super();
    }

    public GiftCertificateNotFoundException(String message) {
        super(message);
    }

    public GiftCertificateNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public GiftCertificateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GiftCertificateNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public GiftCertificateNotFoundException(Throwable cause) {
        super(cause);
    }

    public GiftCertificateNotFoundException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }
}