package com.employee_management.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
