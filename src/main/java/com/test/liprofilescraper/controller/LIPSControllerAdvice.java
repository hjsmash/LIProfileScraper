package com.test.liprofilescraper.controller;

import com.test.liprofilescraper.exceptions.LIPSUniversalException;
import com.test.liprofilescraper.model.ErrorResponseBody;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class LIPSControllerAdvice {

    @ExceptionHandler(LIPSUniversalException.class)
    public ResponseEntity<ErrorResponseBody> handleUniversalException(LIPSUniversalException ex) {
        ErrorResponseBody errorResponseBody = new ErrorResponseBody(ex.getMessage(), 400);
        return new ResponseEntity<>(errorResponseBody, HttpStatus.BAD_REQUEST);
    }
}
