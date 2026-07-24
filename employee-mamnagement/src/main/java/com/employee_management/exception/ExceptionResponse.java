package com.employee_management.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {
    private int status;
    private String message;
    private LocalDateTime time;
}
