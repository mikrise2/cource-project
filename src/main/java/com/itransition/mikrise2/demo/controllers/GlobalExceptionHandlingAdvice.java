package com.itransition.mikrise2.demo.controllers;

import com.itransition.mikrise2.demo.exception.CompanyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlingAdvice {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> error(final RuntimeException e) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("internal server error");
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> error(final CompanyNotFoundException e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
