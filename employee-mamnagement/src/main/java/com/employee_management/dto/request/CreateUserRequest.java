package com.employee_management.dto.request;

import com.employee_management.entity.Role;
import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
}