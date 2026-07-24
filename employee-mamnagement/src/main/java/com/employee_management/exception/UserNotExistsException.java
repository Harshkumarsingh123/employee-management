package com.employee_management.exception;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String message){
        super(message);
    }
}
