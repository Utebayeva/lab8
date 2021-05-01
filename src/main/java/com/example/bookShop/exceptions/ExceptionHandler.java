package com.example.bookShop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = IncorrectNumberException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectNumberException(IncorrectNumberException ine) {
        ErrorResponse errorResponse = new ErrorResponse("Error", ine.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
