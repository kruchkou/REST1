package com.epam.esm.controller;

import com.epam.esm.model.util.ExceptionResponse;
import com.epam.esm.service.exception.GiftCertificateDataValidationException;
import com.epam.esm.service.exception.GiftCertificateNotFoundException;
import com.epam.esm.service.exception.TagDataValidationException;
import com.epam.esm.service.exception.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HTTP_STATUS);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(IllegalArgumentException e) {
        final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HTTP_STATUS);
    }

    @ExceptionHandler(GiftCertificateDataValidationException.class)
    public ResponseEntity<ExceptionResponse> handleGiftCertificateDataValidationException(
            GiftCertificateDataValidationException e) {

        final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HTTP_STATUS);
    }

    @ExceptionHandler(TagDataValidationException.class)
    public ResponseEntity<ExceptionResponse> handleTagDataValidationException(TagDataValidationException e) {
        final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HTTP_STATUS);
    }


    @ExceptionHandler(GiftCertificateNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleGiftCertificateNotFoundException(GiftCertificateNotFoundException e) {
        final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HTTP_STATUS);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTagNotFoundException(TagNotFoundException e) {
        final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HTTP_STATUS);
    }
}