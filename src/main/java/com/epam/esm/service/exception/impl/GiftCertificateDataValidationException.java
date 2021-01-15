package com.epam.esm.service.exception.impl;

import com.epam.esm.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "GiftCertificate data validation failed")
public class GiftCertificateDataValidationException extends ServiceException {

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
