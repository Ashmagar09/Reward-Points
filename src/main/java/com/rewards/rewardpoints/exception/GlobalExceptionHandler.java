package com.rewards.rewardpoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDto> handleValidationException(ValidationException e){
        ErrorDto error = new ErrorDto();
        error.setMessage(e.getMessage());
        error.setErrorCode("400");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoTransactionFoundException.class)
    public ResponseEntity<ErrorDto> handleNoTransactionFoundException(NoTransactionFoundException e){
        ErrorDto error = new ErrorDto();
        error.setMessage(e.getMessage());
        error.setErrorCode("400");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
