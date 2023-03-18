package com.tronina.proxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;


@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BuisenesLogicException.class)
    public ResponseEntity<String> handleNotFound(BuisenesLogicException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handle3(HttpClientErrorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Third party service cannot process the request");
    }


    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleServerException(HttpServerErrorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Third party service not available");
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handle5(RestClientException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Third party service error");
    }
}

