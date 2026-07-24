package com.employee_management.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> userAlreadyExists(HttpServletRequest request,
                                                               UserAlreadyExistsException ex){

        ExceptionResponse exceptionResponse= ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(HttpServletRequest request,
                                                                   UserNotExistsException exception){
        ExceptionResponse exceptionResponse=ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
